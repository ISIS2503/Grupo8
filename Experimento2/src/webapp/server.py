import traceback

import sys
from flask import Flask, Response, request
import datetime
import time
from  sched import scheduler
from datetime import timedelta
from kafka import KafkaProducer
from kafka.errors import KafkaError

app = Flask(__name__)

# {
#   { (nivel, area):
#       (id_mc, tipo), (Bool, [
#                               (dato, timeStamp)
#                             ]
#                       )
#       )
#       ....
#    }
#    ...
# }
structure = {}
areas_alerta = {}
ciclos = {}

ref = {
    'Temperatura': {
        'freq': 0,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Gases(CO)': {
        'freq': 0,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Iluminacion': {
        'freq': 0,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Ruido': {
        'freq': 0,
        'lim_inf': 0,
        'lim_sup': 0,
    }
}

hora_inicio = 5
hora_fin = 17

sbj_alerta1 = "Alerta! Sensor Fuera de Linea"
sbj_alerta3 = "Alerta! Actuador Ineficiente"
receivers = ['destinatario1@mail.com', 'destinatario2@mail.com']
event = scheduler(time.time, time.sleep)


@app.route('/generarAlerta', methods=['POST'])
def rules():
    response = Response(content_type='text/plain', status=500)

    dato = request.get_json()

    try:
        if 'valor' in dato:
            add_to_queue(dato)

            offline(dato)
            out_of_range(dato)
            response.status_code = 200
    except:
        traceback.print_exc(file=sys.stdout)

    return response


def offline(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['tipo']
    nivel = dato['metadata']['nivel']
    area = dato['metadata']['area']

    queue = structure[(nivel, area)][(id_mc, tipo)][1]
    now = datetime.datetime.now()
    ultimo = queue[-1]
    timestamp = ultimo[1]

    temp = 5 * ref[tipo]['freq']
    d = timedelta(seconds=temp)
    timestamp += d

    if timestamp < now:
        alerta(1, dato)


def out_of_range(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['tipo']
    nivel = dato['metadata']['nivel']
    area = dato['metadata']['area']

    tupla = structure[(nivel, area)][(id_mc, tipo)]
    suma = 0
    for elem in tupla[1]:
        suma += elem[0]
    prom = suma / len(tupla[1])

    if prom < ref[tipo]['lim_inf'] or prom > ref[tipo]['lim_sup']:
        alerta(2, dato)
        tupla[0] = 1
    else:
        tupla[0] = 0


def alerta(alerta_tipo, data):
    tipo = data['tipo']
    nivel = data['metadata']['nivel']
    area = data['metadata']['area']

    # producer = KafkaProducer(value_serializer=lambda m: json.dumps(m).encode('ascii'), bootstrap_servers=['localhost:8090'])
    alerta = {'alerta': alerta_tipo,
              'data': data
              }
    # producer.send('alertas', alerta)

    if alerta_tipo == 2:
        if (nivel, area, tipo) not in areas_alerta:
            areas_alerta[(nivel, area, tipo)] = [1, datetime.datetime.now()]
            # TODO ENCENDER ACTUADOR
            print('Alerta 2', tipo, data['valor'], ref[tipo])
        else:
            d = timedelta(minutes=10)
            alerta = areas_alerta[(nivel, area, tipo)]
            now = datetime.datetime.now()
            if (now >= alerta[1] + d):
                verify(data)


def calcular_rango(arr):
    # arr_i = {name, min, max, var, freq}

    for item in arr:
        ref_i = ref[item['name']]

        ref_i['freq'] = item['freq']
        ref_i['lim_inf'] = item['min'] - item['var']
        ref_i['lim_sup'] = item['max'] - item['var']


def add_to_queue(dato):
    metadata = dato['metadata']
    tipo = dato['tipo']
    value = dato['valor']
    timestamp = dato['timeStamp']

    timestamp = datetime.datetime.strptime(timestamp, '%Y-%m-%dT%H:%M:%S.%fZ')

    key = (metadata['nivel'], metadata['area'])
    if not key in structure:
        structure[key] = {}
    mcs = structure[key]

    key2 = (metadata['microcontrolador'], tipo)
    if not key2 in mcs:
        mcs[key2] = [0, []]
    datos_mc = mcs[key2]

    if len(datos_mc[1]) == 10:
        datos_mc[1].pop(0)
    datos_mc[1].append((value, timestamp))

    mcs[key2] = datos_mc
    structure[key] = mcs


def initial_config():
    arr = []
    arr.append({'name': 'Temperatura', 'min': 21.5, 'max': 27.0, 'var': 5.4, 'freq': 60})
    arr.append({'name': 'Ruido', 'min': 0, 'max': 85.0, 'var': 0, 'freq': 120})
    arr.append({'name': 'Gases(CO)', 'min': 0, 'max': 100.0, 'var': 0, 'freq': 60})
    arr.append({'name': 'Iluminacion', 'min': 100, 'max': 2000, 'var': 0, 'freq': 120})
    calcular_rango(arr)


def prueba():
    now = datetime.datetime.now()
    initial_config()
    message = {
        "sensetime": datetime.datetime(now.year, now.month, now.day, now.hour, now.minute, now.second,
                                       0) - datetime.timedelta(seconds=(60 * 5)),
        "variablesAmbientales": [
            {
                "data": 19,
                "tipo": "Temperatura",
                "unidad": "C"
            },
            {
                "data": 731,
                "tipo": "Gases(CO)",
                "unidad": "ppm"
            },
            {
                "data": 65536,
                "tipo": "Iluminacion",
                "unidad": "Lux"
            },
            {
                "data": 58,
                "tipo": "Ruido",
                "unidad": "dB"
            }
        ],
        "metadata": {
            "nivelId": "1",
            "areaId": "1",
            "microcontroladorId": "1"
        }
    }

    sensetime = message['sensetime']
    value = list(message['variablesAmbientales'])
    ids = message['metadata']
    nivel_id = ids['nivelId']
    area_id = ids['areaId']
    microcontrolador_id = ids['microcontroladorId']

    for elem in value:
        rules({
            'valor': elem['data'],
            'tipo': elem['tipo'],
            'timeStamp': sensetime,
            'metadata': {
                'nivel': nivel_id,
                'area': area_id,
                'microcontrolador': microcontrolador_id,
            }
        })


def verify(data):
    tipo = data['tipo']
    nivel = data['metadata']['nivel']
    area = data['metadata']['area']
    microcontrolador = data['metadata']['microcontrolador']

    activo = False
    if (nivel, area, tipo) in areas_alerta:
        area_tipo = areas_alerta[(nivel, area, tipo)]
        for elem in structure[(nivel, area)]:  # Si esta en alerta se miran todos los sensores
            hayAlerta = elem[0]
            if hayAlerta == 1:  # Si acualquiera de los sensores genera alerta
                activo = True
                if area_tipo[0] >= 6:
                    # Si se ha activado 6 veces
                    alerta(3, data)
                    areas_alerta[(nivel, area, tipo)][0] = 1  # Volvemos a 1 para verificar despues de enviar el correo
                else:
                    areas_alerta[(nivel, area, tipo)][0] += 1  # Aumentamos porque se vuelve a activar por 10 minutos
        if not activo:
            # Saca el area de la lista de alertas
            areas_alerta.pop([(nivel, area, tipo)], None)
            # TODO ACA DEBE IR CODIGO PARA APAGAR EL ACTUADOR
        else:
            areas_alerta[(nivel, area, tipo)][
                1] = datetime.datetime.now()  # Agendamos un verify sobre el area en 10 min


if __name__ == '__main__':
    app.run()
