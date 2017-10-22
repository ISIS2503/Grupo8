import json
import time

import msgpack
from kafka import KafkaConsumer

function_on_consumer = lambda x: x


def init_consumer():
    # To consume latest messages and auto-commit offsets
    consumer = KafkaConsumer('alta.piso1.local1', group_id='my-group', bootstrap_servers=['localhost:8090'])

    for message in consumer:
        print("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                             message.offset, message.key,
                                             message.value))

        s = str(message.value)
        string = 'data":'
        init = s.index(string)
        value = float(s[init + len(string):s.index(',', init)])

        sensor = {
            "dataValue": value,
            "idSensor": 1,
            "samplingTime": time.time()
        }
        function_on_consumer(sensor)

    # consume earliest available messages, don't commit offsets
    KafkaConsumer(auto_offset_reset='earliest', enable_auto_commit=False)
    # consume json messages
    KafkaConsumer(value_deserializer=lambda m: json.loads(m.decode('ascii')))
    # consume msgpack
    KafkaConsumer(value_deserializer=msgpack.unpackb)
    # StopIteration if no message after 1sec
    KafkaConsumer(consumer_timeout_ms=1000)


def on_consumer(function):
    function_on_consumer = function


if __name__ == '__main__':
    init_consumer()
