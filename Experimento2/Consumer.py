import json

import msgpack
from kafka import KafkaConsumer

function_on_consumer = lambda x: x


def init_consumer():
    # To consume latest messages and auto-commit offsets
    consumer = KafkaConsumer('n.+.a.+.m.+',group_id='variablesAmbientales',bootstrap_servers=['localhost:8090'])

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
            function_on_consumer(payload)

    # consume earliest available messages, don't commit offsets
    KafkaConsumer(auto_offset_reset='earliest', enable_auto_commit=False)
    # consume json messages
    KafkaConsumer(value_deserializer=lambda m: json.loads(m.decode('ascii')))
    # consume msgpack
    KafkaConsumer(value_deserializer=msgpack.unpackb)


def on_consumer(funcion):
    global function_on_consumer
    function_on_consumer = funcion


if __name__ == '__main__':
    init_consumer()
