import datetime

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


def rules(dato):
    offline(dato)
    out_of_range(dato)
    inefficient(dato)
    return None


def offline(dato):
    id_mc = dato['metadata']['microcontrolador']
    tipo = dato['metadata']['tipo']
    area = dato['metadata']['area']

    queue = structure[area][(id_mc, tipo)][1]
    if not queue:
        queue = []

    timestamp = None
    now = datetime.datetime.now()
    if len(queue) != 0:
        ultimo = queue.pop(len(queue) - 1)
        timestamp = ultimo['timestamp']
    else:
        timestamp = datetime.datetime(now.year, now.month, now.day, 5, 0, 0, 0)

    temp = 5 * ref[tipo]['freq']
    timestamp += datetime.timedelta(seconds=temp)

    if timestamp < now:
        alerta(1, dato['metadata'])


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
    # TODO
    None


def alerta(tipo, data):
    if tipo == 1:
        # Correo
        # TODO
        None
    elif tipo == 2:
        # TODO
        # Actuador data['metadata']['area']. algo = dato
        area_tipo = areas_alerta[(data['metadata']['area'], data['tipo'])]
        if not area_tipo:
            areas_alerta[(data['metadata']['area'], data['tipo'])] = (1, datetime.datetime.now())
            # TODO
            # Actuador
        None
    elif tipo == 3:
        # TODO
        # Correo
        None
    None


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
