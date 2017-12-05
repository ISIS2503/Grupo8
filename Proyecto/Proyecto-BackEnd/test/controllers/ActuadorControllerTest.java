package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.main.Actuador;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Result;
import play.test.Helpers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

/**
 * Created by s.cortes on 27/09/2017.
 */
public class ActuadorControllerTest {

    private static final String UTF_8 = "utf-8";

    //@Test
    public void testCreate() {

        running(fakeApplication(), ()->{
            Actuador actuador = new Actuador();
            actuador.setId(1L);
            actuador.setActivo(true);
            actuador.setIdArea(1L);
            actuador.setInicio(new Date());


            Call action = controllers.routes.ActuadorController.create(actuador.getIdArea());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(actuador)));

            //assertEquals("El actuador no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del actuador no es el esperado", (long) actuador.getId(), json.get("id").asLong());
            //assertEquals("El atributo activo del actuador no es el esperado",  actuador.getActivo(), json.get("activo").asBoolean());
            //assertEquals("El idArea del actuador no es el esperado", actuador.getIdArea(), json.get("idArea").asLong());
        });

    }

    //@Test
    public void testRetrieve() {

        running(fakeApplication(), ()-> {
            Actuador actuador = new Actuador();
            actuador.setId(1L);
            actuador.setActivo(true);
            actuador.setIdArea(1L);
            actuador.setInicio(new Date());

            Call action = controllers.routes.ActuadorController.create(actuador.getIdArea());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(actuador)));
            Result result = route(Helpers.fakeRequest(routes.ActuadorController.retrieve(actuador.getId()))); //TODO controllers.

            //assertEquals("El actuador no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del actuador no es el esperado", (long) actuador.getId(), json.get("id").asLong());
            //assertEquals("El atributo activo del actuador no es el esperado",  actuador.getActivo(), json.get("activo").asBoolean());
            //assertEquals("El idArea del actuador no es el esperado", actuador.getIdArea(), json.get("idArea").asLong());
        });

    }

    //@Test
    public void testRetrieveAll() {

        running(fakeApplication(), ()-> {
            int cant = 10;
            List<Actuador> actuadors = new ArrayList<>();

            for (int i = 0; i < cant; i++) {
                Actuador actuador = new Actuador();
                actuador.setId((long)i);
                actuador.setActivo((i%2==0));
                actuador.setIdArea((long)i);
                actuador.setInicio(new Date());

                Call action = controllers.routes.ActuadorController.create(actuador.getIdArea());
                Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(actuador)));
            }
            for (int i = 0; i < cant; i++) {
                Actuador actuador = actuadors.get(i);
                Result result = route(Helpers.fakeRequest(routes.ActuadorController.retrieve(actuador.getId()))); //TODO controllers.

                //assertEquals("El actuador no fue creado correctamente.", Http.Status.OK, result.status());
                //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                //assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                //assertEquals("El id del actuador no es el esperado", (long) actuador.getId(), json.get("id").asLong());
                //assertEquals("El atributo activo del actuador no es el esperado",  actuador.getActivo(), json.get("activo").asBoolean());
                //assertEquals("El idArea del actuador no es el esperado", actuador.getIdArea(), json.get("idArea").asLong());
            }
        });
    }

    //@Test
    public void testUpdate() {
        running(fakeApplication(), ()-> {
            Actuador actuador = new Actuador();
            actuador.setId(1L);
            actuador.setActivo(true);
            actuador.setIdArea(1L);
            actuador.setInicio(new Date());

            Call action = controllers.routes.ActuadorController.create(actuador.getIdArea());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(actuador)));

            actuador = new Actuador();
            actuador.setId(2L);
            actuador.setActivo(false);
            actuador.setIdArea(2L);
            actuador.setInicio(new Date());

            action = controllers.routes.ActuadorController.update(actuador.getId()); // TODO controllers.
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(actuador)));

            //assertEquals("El actuador no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id del actuador no es el esperado", (long) actuador.getId(), json.get("id").asLong());
            //assertEquals("El atributo activo del actuador no es el esperado",  actuador.getActivo(), json.get("activo").asBoolean());
            //assertEquals("El idArea del actuador no es el esperado", actuador.getIdArea(), json.get("idArea").asLong());
        });
    }
}
