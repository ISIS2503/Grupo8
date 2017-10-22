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
    add_to_queue(dato['metadata'], dato['tipo'], dato['valor'])

    offline(dato)
    out_of_range(dato)
    inefficient(dato)


def offline(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['metadata']['tipo']
    area = dato['metadata']['area']

    queue = structure[area][(id_mc, tipo)][1]
    if not queue:
        queue = []

    timestamp = None
    now = datetime.datetime.now()
    if len(queue) == 0:
        timestamp = datetime.datetime(now.year, now.month, now.day, hora_inicio, 0, 0, 0)
    else:
        ultimo = queue.pop(len(queue) - 1)
        timestamp = ultimo['timestamp']

    temp = 5 * ref[tipo]['freq']
    timestamp += datetime.timedelta(seconds=temp)

    if timestamp < now:
        alerta(1, dato)


def out_of_range(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['metadata']['tipo']
    area = dato['metadata']['area']

    tupla = structure[area][(id_mc, tipo)]
    if len(tupla) >= 10:
        suma = 0
        for elem in tupla:
            suma += elem['dato']
        prom = suma / 10

        if prom < ref[tipo]['lim_inf'] or prom > ref[tipo]['lim_sup']:
            alerta(2, dato['metadata'])
            tupla[0] = True
        else:
            tupla[0] = False


def inefficient(dato):
    alerta(3, dato)


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
        # TODO
        # Actuador data['metadata']['area']. algo = dato
        area_tipo = areas_alerta[(data['metadata']['area'], data['tipo'])]
        if not area_tipo:
            areas_alerta[(data['metadata']['area'], data['tipo'])] = (1, datetime.datetime.now())
            # TODO
            # Actuador
    elif tipo == 3:
        metadata = data['metadata']
        msg_body = 'El actuador en el area: ' + metadata['area'] + ', ha estado encendido por 1 hora'
        util.sendTo(receivers, None, sbj_alerta3, msg_body)


def calcular_rango(arr):
    # arr_i = {min, max, var, freq}
    for i in range(len(arr)):
        ref[i]['freq'] = arr[i]['freq']

        ref[i]['lim_inf'] = arr[i]['min'] - arr[i]['var']
        ref[i]['lim_sup'] = arr[i]['max'] - arr[i]['var']


def add_to_queue(metadata, tipo, value):
    mcs = structure[metadata['area']]
    datos_mc = mcs[(metadata['microcontrolador'], tipo)]

    if not datos_mc:
        datos_mc = (False, [])

    if len(datos_mc[1]) == 10:
        datos_mc[1].pop(0)
    datos_mc[1].append(value)

    mcs[(metadata['microcontrolador'], tipo)] = datos_mc
