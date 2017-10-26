import json
import requests
from kafka import KafkaConsumer
import E2Utils as util


sbj_alerta1 = "Alerta! Sensor Fuera de Linea"
sbj_alerta3 = "Alerta! Actuador Ineficiente"
receivers = ['destinatario1@mail.com', 'destinatario2@mail.com']

consumer = KafkaConsumer('alertas',
						 value_deserializer=lambda m: json.loads(m.decode('ascii'))
                         group_id='variablesAmbientales',
                         bootstrap_servers=['localhost:8090'])

for message in consumer:
    json_data = message.value
	tipoAlerta = json_data['alerta']
	data = json_data['data']
	
	tipo = data['tipo']
    nivel = data['metadata']['nivel']
    area = data['metadata']['area']
	
	if tipoAlerta == 1:
        metadata = data['metadata']
        msg_body = 'El sensor de ' + tipo + \
                   ', en la ubicación: \nNivel:' + metadata['nivel'] + \
                   '\nArea: ' + metadata['area'] + \
                   '\nMicrocontrolador: ' + metadata['microcontrolador'] + 'se encuentra fuera de linea.'
				   + 'Hora en que se presenta la situación: ' + data['timeStamp']
        util.sendTo(receivers, None, sbj_alerta1, msg_body)
        print('Alerta 1')
	elif tipoAlerta == 2:
        # Actuador data['metadata']['area']. algo = dato
		msg_body = 'El sensor de ' + tipo + \
                   ', en la ubicación: \nNivel:' + metadata['nivel'] + \
                   '\nArea: ' + metadata['area'] + \
                   '\nMicrocontrolador: ' + metadata['microcontrolador'] + 
				   'presento un promedio de valores fuera del rango permitido: ' + data['valor'] +
				   '.Hora en la que registro el ultimo valor: ' + data['timeStamp']				   
  				   
    elif tipoAlerta == 3:
        metadata = data['metadata']
        msg_body = 'El actuador en el area: ' + metadata['area'] + ', ha estado encendido por 1 hora.'
        util.sendTo(receivers, None, sbj_alerta3, msg_body)
	
   

