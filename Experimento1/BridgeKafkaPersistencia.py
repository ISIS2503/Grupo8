import json
import requests
from kafka import KafkaConsumer
 
consumer = KafkaConsumer('n1.a1.m1',
                         group_id='variablesAmbientales',
                         bootstrap_servers=['localhost:8090'])
 
for message in consumer:
	json_data = json.loads(message.value.decode('utf-8'))
	sensetime = json_data['sensetime']
	value = json_data['variablesAmbientales']
	ids = value[0]
	nivelId = ids['nivelId']
	areaId = ids['areaId']
	microcontroladorId = ids['microcontroladorId']
	
	for i in range(len(value)):
		url = 'http://localhost:9000/minas/nivel/' + nivelId + '/areas/' + areaId + '/microcontroladores/'+microcontroladorId + '/sensor/'+ (i+1)+'/datos'
		payload = {
		'valor': (value[i+1])['data'],
		'timeStamp':sensetime
		}
		
		response = requests.post(url, data=json.dumps(payload),
							 headers={'Content-type': 'application/json'})
		print(message.topic)
		print("Response Status Code: " + str(response.status_code))