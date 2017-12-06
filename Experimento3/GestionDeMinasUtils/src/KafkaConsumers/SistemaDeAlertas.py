import sys
import traceback

import datetime
from datetime import timedelta
from kafka import KafkaProducer
import time
import threading
import schedule
import json
from kafka import KafkaConsumer



def run_continuously(self, interval=1):
    cease_continuous_run = threading.Event()

    class ScheduleThread(threading.Thread):
        @classmethod
        def run(cls):
            while not cease_continuous_run.is_set():
                self.run_pending()
                time.sleep(interval)

    continuous_thread = ScheduleThread()
    continuous_thread.start()
    return cease_continuous_run

def rules(dato):

    try:
        if 'valor' in dato:
            add_to_queue(dato)

            offline(dato)
            out_of_range(dato)

    except:
        traceback.print_exc(file=sys.stdout)

consumer = KafkaConsumer('n.+.a.+.m.+',
                         group_id='variablesAmbientales',
                         bootstrap_servers=['localhost:8090'])

for message in consumer:
    json_data = json.loads(message.value.decode('utf-8'))
    sensetime = json_data['sensetime']
    value = json_data['variablesAmbientales']
    ids = json_data['metadata']
    nivel_id = ids['nivelId']
    area_id = ids['areaId']
    microcontrolador_id = ids['microcontroladorId']

    for elem in value:
        payload = {
            'valor': elem['data'],
            'tipo': elem['tipo'],
            'timeStamp': sensetime,
            'metadata': {
                'nivel': nivel_id,
                'area': area_id,
                'microcontrolador': microcontrolador_id
            }
        }
        rules(payload)

def offline():
    now = datetime.datetime.now()
    for elem in structure:
        tipo = elem[1]
        for mc in elem:
            n = len(mc[1])
            if n == 0:
                print('se identifico alerta 1')
            else:
                ultimo = mc[1][-1]
                timestamp = ultimo[1]
                temp = 5*ref[tipo]['freq']
                d = timedelta(seconds=temp)
                timestamp += d
                if timestamp < now:
                    print('se identifico alerta 1')

schedule.every(60).seconds.do(offline)
run_continuously(schedule, 1)


structure = {}
areas_alerta = {}

ref = {
    'Temperatura': {
        'freq': 60,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Gases(CO)': {
        'freq': 60,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Iluminacion': {
        'freq': 120,
        'lim_inf': 0,
        'lim_sup': 0,
    },
    'Ruido': {
        'freq': 120,
        'lim_inf': 0,
        'lim_sup': 0,
    }
}


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
		alerta(2, dato, prom)
		print('Se identifico alerta 2')
		tupla[0] = 1
	else:
		tupla[0] = 0


def alerta(alerta_tipo, data, prom):
    tipo = data['tipo']
    nivel = data['metadata']['nivel']
    area = data['metadata']['area']

    alerta = {'alerta': alerta_tipo,
              'data': data
              }

    if alerta_tipo == 2:
        if (nivel, area, tipo) not in areas_alerta:
            areas_alerta[(nivel, area, tipo)] = [1, datetime.datetime.now()]
            # TODO ENCENDER ACTUADOR
            alerta = {'alerta': alerta_tipo,
                      'data': data,
                      'promedio': prom
                      }
        else:
            d = timedelta(minutes=10)
            areasAlerta = areas_alerta[(nivel, area, tipo)]
            now = datetime.datetime.now()
            if (now >= areasAlerta[1] + d):
                verify(data)

    producer = KafkaProducer(value_serializer=lambda m: json.dumps(m).encode('ascii'),
                             bootstrap_servers=['localhost:8090'])
    producer.send('alertas', alerta)


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
                    alerta(3, data, 0)
                    print('se identifico alerta 3')
                    areas_alerta[(nivel, area, tipo)][0] = 1  # Volvemos a 1 para verificar despues de enviar el correo
                else:
                    areas_alerta[(nivel, area, tipo)][0] += 1  # Aumentamos porque se vuelve a activar por 10 minutos
        if not activo:
            # Saca el area de la lista de alertas
            areas_alerta.pop([(nivel, area, tipo)], None)
        # TODO ACA DEBE IR CODIGO PARA APAGAR EL ACTUADOR
        else:
            areas_alerta[(nivel, area, tipo)][1] = datetime.datetime.now()  # Agendamos un verify sobre el area en 10 min

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

class Alerta:
    def __init__( s, seed_modifier ):
        s.seed_modifier = seed_modifier
    def generarAlerta(data):
        msg_body = 'El sensor de ' + data['tipo'] + \
                   ', en la ubicación: \nNivel:' + data['nivel'] + \
                   '\nArea: ' + data['area'] + \
                   '\nMicrocontrolador: ' + data['microcontrolador'] + 'se encuentra en alerta tipo: '+ data['tipoAlerta']
        return msg_body

class NotificacionExtraDecorator:
    def __init__( s, decorated ):
        s.decorated = decorated
    def generarAlerta(s, data):
        return s.decorated.generarAlerta(data)
    def notificacionExtra(data):
        return alerta(data)

class ActuadorDecorator:
    def __init__( s, decorated):
        s.decorated = decorated
    def generarAlerta(s, data):
        return s.decorated.generarAlerta(data)


class Actuador:
    def __init__(self, state):
        self._state = state

    def request(self):
        self._state.handle()


class State():
    def handle(self):
        self.request()
        pass

class Encendido(State):
      def handle(self):
        #EncenderActuador
        pass


class Apagado(State):
    def handle(self):
        #ApagarActuador
        pass






