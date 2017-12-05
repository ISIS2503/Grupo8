package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.main.Dato;
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


public class DatoControllerTest {

    private static final String UTF_8 = "utf-8";

    @Test
    public void testCreate()
    {
        running(fakeApplication(), ()->{
            Dato dato = new Dato();
            dato.setId(1L);
            dato.setValor(2f);
            dato.setIdSensor(1L);


            Call action = controllers.routes.DatoController.create(dato.getIdSensor());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(dato)));


            assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
            assertEquals("El valor del dato no es el esperado", (float) dato.getValor(), json.get("valor").floatValue, 0);
            //assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());

        });

    }

    @Test
    public void testRetrieve() {

        running(fakeApplication(), ()->{
            Dato dato = new Dato();
            dato.setId(1L);
            dato.setValor(2f);
            dato.setIdSensor(1L);


            Call action = controllers.routes.DatoController.create(dato.getIdSensor());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(dato)));
            Result result = route(Helpers.fakeRequest(controllers.routes.DatoController.retrieve(dato.getId())));


            assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
            assertEquals("El valor del dato no es el esperado", (float) dato.getValor(), json.get("valor").floatValue(), 0);
            //assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());

        });

    }


    @Test
    public void testRetrieveAll() {
        running(fakeApplication(), ()-> {
                    int cant = 10;
                    List<Dato> datos = new ArrayList<>();
                    for (int i = 0; i < cant; i++) {

                        for (int i = 0; i < cant; i++) {
                            Dato dato = new Dato();
                            dato.setId((long) i);
                            dato.setValor((float) i);
                            dato.setIdSensor((long) i);
                            datos.add(dato);

                        Call action = controllers.routes.DatoController.create(dato.getIdSensor());
                        Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(dato)));

                    }
                        for (int i = 0; i < cant; i++) {
                            Dato dato = datos.get(i);
                            Result result = controller.retrieve(dato.getId());

                            assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
                            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                            assertEquals(UTF_8, result.charset());

                            byte[] body = JavaResultExtractor.getBody(result, 0L);
                            JsonNode json = Json.parse(body);

                            assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
                            assertEquals("El valor del dato no es el esperado", (float) dato.getValor(), json.get("valor").floatValue(), 0);
                            //assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
                    }

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

            Dato dato = new Dato();
            dato.setId(1L);
            dato.setValor(2f);
            dato.setIdSensor(1L);

            Call action = controllers.routes.AlertaController.create(alerta.getIdArea());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));

            dato.setId(2L);
            dato.setValor(4f);
            dato.setIdSensor(2L);

            action = controllers.routes.AlertaController.update(alerta.getId());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(alerta)));

        assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (float) dato.getValor(), json.get("valor").floatValue(), 0);
        //assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (float) dato.getValor(), json.get("valor").floatValue(), 0);
        //assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
    }

    @Test
    public void testDelete()
    {

    }

}
