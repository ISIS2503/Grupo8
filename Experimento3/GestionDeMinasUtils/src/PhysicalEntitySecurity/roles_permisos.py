import hashlib
import redis

r = redis.Redis()

def definirPermiso(rol, topico, permiso):
    
    if(r.exists(topico)):
        r.hset(topico, rol, permiso)
    else:
        value = {rol: permiso}
        r.hmset(topico, value)

def definirUsuario(nombreUsuario, contrasenia, rol):
    btContrasenia = bytes(contrasenia, 'utf-8')
    md5Contrasenia = hashlib.md5(btContrasenia).hexdigest()
    usuario = {"contrasenia": md5Contrasenia, "rol": rol}
    r.hmset(nombreUsuario, usuario) 

def definirRol(rol, identificador):
    #Solo lo crea si no existe. De esta forma se preserva la integridad.
    r.set(rol, identificador, nx=True)

#Ejemplo de definicion de roles

definirRol("administrador", "ad")
definirRol("microcontrolador", "mc")
definirRol("consumidor","cm")

#Ejemplo de definicion de permisos de cada rol

definirPermiso("ad", "n.1.a.1.m.1", 3)
definirPermiso("mc", "n.1.a.1.m.1", 2)
definirPermiso("cm", "n.1.a.1.m.1", 1)
definirPermiso("cm", "iot_topic", 1)
definirPermiso("mc", "iot_topic", 2)
definirPermiso("cm", "n.1.a.1.m.1", 1)

#Ejemplo de asociacion de cada usuario con su rol

definirUsuario("fernando", "Isis2503.", "ad")
definirUsuario("m1", "1234", "mc")
definirUsuario("m2", "5678", "mc")
definirUsuario("m3", "9101", "mc")
definirUsuario("m4", "1121", "mc")
definirUsuario("k1", "3141", "cm")




