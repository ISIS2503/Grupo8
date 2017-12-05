package controllers;


import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

import actions.Roles;
import com.fasterxml.jackson.databind.JsonNode;
import models.users.Rol;
import models.users.Usuario;
import org.junit.Before;
import org.junit.Test;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by s.cortes on 26/09/2017.
 */
public class UsuarioControllerTest {

    private static final String UTF_8 = "utf-8";


    private Usuario credentialUser = null;


    public void setup(){
        if(credentialUser == null) {
            List<Usuario> users = Usuario.find.findList();
            boolean finished = false;
            for (int i = 0; i < users.size() && !finished; i++) {
                Usuario user = users.get(i);
                for (int j = 0; j < user.getRoles().size() && !finished; j++) {
                    if (user.getRoles().get(j).getName().equals(Roles.ADMIN.toString())) {
                        credentialUser = user;
                        finished = true;
                    }
                }
            }
        }
    }

    private void createContext(){
        if(credentialUser != null){
            Map<String,String> flashData = Collections.emptyMap();
        }
    }

    @Test
    public void testCreate() {

        running(fakeApplication(), ()->{
            setup();

            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            List<Rol> roles = new ArrayList<>();
            Rol rol = new Rol();
            rol.setName(Roles.ADMIN.toString());
            rol.setId(1L);
            roles.add(rol);
            usuario.setRoles(roles);

            Call action = controllers.routes.UsuarioController.create();
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });

    }

    @Test
    public void testRetrieve() {

        running(fakeApplication(), ()-> {
            setup();


            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            List<Rol> roles = new ArrayList<>();
            Rol rol = new Rol();
            rol.setName(Roles.ADMIN.toString());
            rol.setId(1L);
            roles.add(rol);
            usuario.setRoles(roles);

            Call action = controllers.routes.UsuarioController.create();
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));
            Result result = route(Helpers.fakeRequest(controllers.routes.UsuarioController.retrieve(usuario.getId())));

            assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });

    }

    @Test
    public void testRetrieveAll() {

        running(fakeApplication(), ()-> {
            setup();

            int cant = 10;
            List<Usuario> usuarios = new ArrayList<>();

            for (int i = 0; i < cant; i++) {
                Usuario usuario = new Usuario();
                usuario.setId((long)i);
                usuario.setLogin("usuario"+i);
                usuario.setPassword("contrasena"+i);
                List<Rol> roles = new ArrayList<>();
                Rol rol = new Rol();
                rol.setName(Roles.ADMIN.toString());
                rol.setId(1L);
                roles.add(rol);
                usuario.setRoles(roles);
                usuarios.add(usuario);

                Call action = controllers.routes.UsuarioController.create();
                Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));
            }
            for (int i = 0; i < cant; i++) {
                Usuario usuario = usuarios.get(i);
                Result result = route(Helpers.fakeRequest(controllers.routes.UsuarioController.retrieve(usuario.getId())));

                assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
                assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
                assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
                assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
                assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
            }
        });
    }

    @Test
    public void testUpdate() {
        running(fakeApplication(), ()-> {
            setup();

            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            List<Rol> roles = new ArrayList<>();
            Rol rol = new Rol();
            rol.setName(Roles.ADMIN.toString());
            rol.setId(1L);
            roles.add(rol);
            usuario.setRoles(roles);

            Call action = controllers.routes.UsuarioController.create();
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            usuario.setId(20L);
            usuario.setLogin("usuario2");
            usuario.setPassword("contrasena2");
            usuario.setRoles( roles);

            action = controllers.routes.UsuarioController.update(usuario.getId()); // TODO controllers.
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });
    }
}
