import base64
import hashlib
import redis
from flask import Flask, Response, request
 
app = Flask(__name__)
r = redis.Redis()
 
@app.route('/auth', methods=['POST'])
def auth():
    response = Response(content_type='text/plain', status=403)
     
    try:
      auth = request.headers.get('Authorization')
      token = auth.split(' ')[1]
      data = base64.b64decode(token).decode("utf-8").split(':')
      username = data[0]     
      password = bytes(data[1], 'utf-8')
      passwordGuardada = r.hget(username, "contrasenia")
      if passwordGuardada.decode('utf-8') == hashlib.md5(password).hexdigest():
          response.status_code = 200
    except:
      traceback.print_exc(file=sys.stdout) 
    return response
 
@app.route('/superuser', methods=['POST'])
def superuser():
    response =  Response(content_type='text/plain', status=403)
    try:
      auth = request.headers.get('Authorization')
      token = auth.split(' ')[1]
      print(token)
      data = base64.b64decode(token).decode("utf-8").split(':')
      username = data[0]
      # password = data[1]
      if username == 'microcontrolador': #  and password == '13579':
          response.status_code = 200
    except:
      pass
    return response
 
@app.route('/acl', methods=['POST'])
def acl(): 
    response =  Response(content_type='text/plain', status=403)
    try:
      username = request.form.get('username')
      topic = request.form.get('topic')
      acc = request.form.get('acc')
      
      #Se obtiene el rol del usuario
      rol = r.hget(username, "rol")

      #Se verifica que tenga los permisos necesarios para realizar la accion solicitada
      permiso = r.hget(topic, rol)

      if permiso.decode('utf-8') == acc or permiso == 3:
          response.status_code = 200
    except:
      pass
    return response
 
if __name__ == '__main__':
    app.run()
