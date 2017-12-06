import json
import requests
from kafka import KafkaConsumer
import E2Utils as util
from kafka.errors import KafkaError

sbj_alerta1 = "Alerta! Sensor Fuera de Linea"
sbj_alerta2 = "Alerta! Medicion fuera de rango"
sbj_alerta3 = "Alerta! Actuador Ineficiente"

receivers = ['s.cortes@uniandes.edu.com', 'd.narvaez11@uniandes.edu.com']

try:
    consumer = KafkaConsumer('alertas', group_id='my_group',
                             bootstrap_servers=['localhost:8090'])
except:
    traceback.print_exc(file=sys.stdout)

for message in consumer:
    print('estado: consumiendo del topico alertas')
    json_data = message.value
    tipoAlerta = json_data['alerta']
    data = json_data['data']

    metadata = data['metadata']
    tipo = data['tipo']
    nivel = metadata['nivel']
    area = metadata['area']
    timeStamp = data['timeStamp']

    payload = {
        'tipo': tipoAlerta,
        'timeStamp': timeStamp,
        'variable': tipo
    }

    if tipoAlerta == 1:
        metadata = data['metadata']
        msg_body = 'El sensor de ' + tipo + \
                   ', en la ubicación: \nNivel:' + nivel + \
                   '\nArea: ' + area + \
                   '\nMicrocontrolador: ' + metadata[
                       'microcontrolador'] + 'se encuentra fuera de linea.' + 'Hora en que se presenta la situación: ' + timeStamp
        util.sendTo(receivers, None, sbj_alerta1, msg_body)
        print('Alerta 1 enviada')

    elif tipoAlerta == 2:
        msg_body = 'El sensor de ' + tipo + \
                   ', en la ubicación: \nNivel:' + str(nivel) + \
                   '\nArea: ' + str(area) + \
                   '\nMicrocontrolador: ' + str(
            metadata['microcontrolador']) + 'presento un promedio de valores fuera del rango permitido: ' + str(
            json_data['promedio']) + \
                   '. \n Hora en la que registro el ultimo valor: ' + str(timeStamp)
        util.sendTo(receivers, None, sbj_alerta2, msg_body)
        print('Alerta 2 enviada')

        payload = {
            'tipo': tipoAlerta,
            'timeStamp': timeStamp,
            'variable': tipo,
            'infoAdicional': json_data['promedio']
        }
    elif tipoAlerta == 3:
        msg_body = 'El actuador en el area: ' + str(metadata['area']) + ', ha estado encendido por 1 hora.'
        util.sendTo(receivers, None, sbj_alerta3, msg_body)
        print('Alerta 3 enviada')

    url = 'http://localhost:9000/minas/areas/' + str(area) + '/alertas'
    response = requests.post(url, data=json.dumps(payload),
                             headers={'Content-type': 'application/json'})

    print("Response Status Code: " + str(response.status_code))





