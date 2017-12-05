package controllers;


import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import com.fasterxml.jackson.databind.JsonNode;
import models.users.Usuario;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Result;
import play.test.Helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.cortes on 26/09/2017.
 */
public class UsuarioControllerTest {

    private static final String UTF_8 = "utf-8";

    //@Test
    public void testCreate() {

        running(fakeApplication(), ()->{
            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            usuario.setRoles( "rol1");

            Call action = controllers.routes.UsuarioController.create();
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            //assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            //assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            //assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });

    }

    //@Test
    public void testRetrieve() {

        running(fakeApplication(), ()-> {
            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            usuario.setRoles( "rol1");

            Call action = controllers.routes.UsuarioController.create();
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));
            Result result = route(Helpers.fakeRequest(controllers.routes.UsuarioController.retrieve(usuario.getId())));

            //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            //assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            //assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            //assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });

    }

    //@Test
    public void testRetrieveAll() {

        running(fakeApplication(), ()-> {
            int cant = 10;
            List<Usuario> usuarios = new ArrayList<>();

            for (int i = 0; i < cant; i++) {
                Usuario usuario = new Usuario();
                usuario.setId((long)i);
                usuario.setLogin("usuario"+i);
                usuario.setPassword("contrasena"+i);
                usuario.setRoles( "rol" + 1);
                usuarios.add(usuario);

                Call action = controllers.routes.UsuarioController.create();
                Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));
            }
            for (int i = 0; i < cant; i++) {
                Usuario usuario = usuarios.get(i);
                Result result = route(Helpers.fakeRequest(controllers.routes.UsuarioController.retrieve(usuario.getId())));

                //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
                //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                //assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                //assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
                //assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
                //assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
                //assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
            }
        });
    }

    //@Test
    public void testUpdate() {
        running(fakeApplication(), ()-> {
            Usuario usuario = new Usuario();
            usuario.setId(100L);
            usuario.setLogin("usuario1");
            usuario.setPassword("contrasena1");
            usuario.setRoles( "rol1");

            Call action = controllers.routes.UsuarioController.create();
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            usuario.setId(20L);
            usuario.setLogin("usuario2");
            usuario.setPassword("contrasena2");
            usuario.setRoles( "rol2");

            action = controllers.routes.UsuarioController.update(usuario.getId()); // TODO controllers.
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(usuario)));

            //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del usuario no es el esperado", (long) usuario.getId(), json.get("id").asLong());
            //assertEquals("El login del usuario no es el esperado",  usuario.getLogin(), json.get("login").asText());
            //assertEquals("El password del usuario no es el esperado", usuario.getPassword(), json.get("password").asText());
            //assertEquals("El rol del usuario no es el esperado", usuario.getRoles(), json.get("rol").asText());
        });
    }
}
