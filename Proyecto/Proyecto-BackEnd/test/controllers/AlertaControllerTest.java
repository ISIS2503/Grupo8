package controllers;



import com.fasterxml.jackson.databind.JsonNode;
import models.main.Alerta;
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

import static org.junit.Assert.assertEquals;

public class AlertaControllerTest {

    private static final String UTF_8 = "utf-8";

    //private AlertaController controller;

    //@Before
    //public void setup() {
    //    controller = new AlertaController();
    //}

    @Test
    public void testCreate()
    {
        running(fakeApplication(), ()->{
            Alerta alerta =new Alerta();
            alerta.setId(1L);
            alerta.setIdArea(2L);
            alerta.setTipo(1L);


            Call action = controllers.routes.AlertaController.create(alerta.getIdArea());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));


            assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);


        });

    }

    @Test
    public void testRetrieve()
    {
        running(fakeApplication(), ()->{
            Alerta alerta =new Alerta();
            alerta.setId(1L);
            alerta.setIdArea(2L);
            alerta.setTipo(1L);


            Call action = controllers.routes.AlertaController.create(alerta.getIdArea());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));
            Result result = route(Helpers.fakeRequest(controllers.routes.AlertaController.retrieve(alerta.getId())));

            assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);


        });

    }

    @Test
    public void testRetrieveAll()
    {

        running(fakeApplication(), ()-> {
            int cant = 10;
            List<Alerta> alertas=new ArrayList<>();
            for (int i = 0; i < cant; i++) {
                Alerta alerta =new Alerta();
                alerta.setId((long)i);
                alerta.setIdArea((long)i);
                alerta.setTipo((int)i);
                alertas.add(alerta);

                Call action = controllers.routes.AlertaController.create(alerta.getIdArea());
                Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));

            }

            for (int i = 0; i < cant; i++) {
                Alerta alerta =alertas.get(i);
                Result result =route(Helpers.fakeRequest(controllers.routes.AlertaController.retrieve(alerta.getId())));

                assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
                assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
                //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
                assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);
            }

        }

    }

    @Test
    public void testUpdate() {

        running(fakeApplication(), ()->{
            //Alerta alerta =new Alerta();
            //alerta.setId(10L);
            //alerta.setIdArea(20L);
            //alerta.setTipo(1);
            //controller.create(alerta.getIdArea());

            Alerta alerta =new Alerta();
            alerta.setId(1L);
            alerta.setIdArea(2L);
            alerta.setTipo(1L);
            Call action = controllers.routes.AlertaController.create(alerta.getIdArea());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));

            alerta.setId(2L);
            alerta.setIdArea(4L);
            alerta.setTipo(2L);

            action = controllers.routes.AlertaController.update(alerta.getId());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));

            assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);

            assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);



        });

    }

    @Test
    public void testDelete()
    {

    }

}
