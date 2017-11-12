import json
import requests

from kafka import KafkaConsumer

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
        url = 'http://127.0.0.1:5000/generarAlerta'
        response = requests.post(url, data=json.dumps(payload),
                                 headers={'Content-type': 'application/json'})
