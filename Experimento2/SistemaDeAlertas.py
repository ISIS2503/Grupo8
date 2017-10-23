import datetime

import E2Utils as util

# {
#   { area:
#       (id_mc, tipo), (Bool, [
#                               (timestamp, dato)
#                             ]
#                       )
#       )
#       ....
#    }
#    ...
# }
structure = {}
areas_alerta = {}
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
receivers = []


def rules(dato):
    if 'valor' in dato:
        add_to_queue(dato)

        offline(dato)
        out_of_range(dato)
        inefficient(dato)


def offline(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['tipo']
    area = dato['metadata']['area']

    queue = structure[area][(id_mc, tipo)][1]

    timestamp = None
    now = datetime.datetime.now()
    if len(queue) == 0:
        timestamp = datetime.datetime(now.year, now.month, now.day, hora_inicio, 0, 0, 0)
    else:
        ultimo = queue[- 1]
        timestamp = ultimo[1]

    temp = 5 * ref[tipo]['freq']
    timestamp += datetime.timedelta(seconds=temp)

    if timestamp < now:
        alerta(1, dato)


def out_of_range(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['tipo']
    area = dato['metadata']['area']

    tupla = structure[area][(id_mc, tipo)]
    suma = 0
    for elem in tupla[1]:
        suma += elem[0]
    prom = suma / len(tupla[1])

    if prom < ref[tipo]['lim_inf'] or prom > ref[tipo]['lim_sup']:
        alerta(2, dato)
        tupla[0] = True
    else:
        tupla[0] = False


def inefficient(dato):
    # alerta(3, dato)
    pass


def alerta(tipo, data):
    if tipo == 1:
        metadata = data['metadata']
        tipo = data['tipo']
        msg_body = 'El sensor de ' + tipo + \
                   ', en la ubicación: \nNivel:' + metadata['nivel'] + \
                   '\nArea: ' + metadata['area'] + \
                   '\nMicrocontrolador: ' + metadata['microcontrolador']
        util.sendTo(receivers, None, sbj_alerta1, msg_body)
    elif tipo == 2:
        # Actuador data['metadata']['area']. algo = dato
        if (data['metadata']['area'], data['tipo']) not in areas_alerta:
            areas_alerta[(data['metadata']['area'], data['tipo'])] = (1, datetime.datetime.now())
            # TODO Actuador
            print('Alerta 2', data['tipo'], data['valor'], ref[data['tipo']])
    elif tipo == 3:
        metadata = data['metadata']
        msg_body = 'El actuador en el area: ' + metadata['area'] + ', ha estado encendido por 1 hora'
        util.sendTo(receivers, None, sbj_alerta3, msg_body)


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

    if not metadata['area'] in structure:
        structure[metadata['area']] = {}
    mcs = structure[metadata['area']]

    if not (metadata['microcontrolador'], tipo) in mcs:
        mcs[(metadata['microcontrolador'], tipo)] = [False, []]
    datos_mc = mcs[(metadata['microcontrolador'], tipo)]

    if len(datos_mc[1]) == 10:
        datos_mc[1].pop(0)
    datos_mc[1].append((value, timestamp))

    mcs[(metadata['microcontrolador'], tipo)] = datos_mc
    structure[metadata['area']] = mcs


def initial_config():
    arr = []
    arr.append({'name': 'Temperatura', 'min': 21.5, 'max': 27.0, 'var': 5.4, 'freq': 60})
    arr.append({'name': 'Ruido', 'min': 0, 'max': 85.0, 'var': 0, 'freq': 120})
    arr.append({'name': 'Gases(CO)', 'min': 0, 'max': 100.0, 'var': 0, 'freq': 60})
    arr.append({'name': 'Iluminacion', 'min': 100, 'max': 2000, 'var': 0, 'freq': 120})
    calcular_rango(arr)


def prueba():
    initial_config()
    message = {
        "sensetime": datetime.datetime.now(),
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

    message = {
        "sensetime": datetime.datetime.now() + datetime.timedelta(hours=100),
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


prueba()
print(structure)
