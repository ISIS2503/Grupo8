package controllers;



import com.fasterxml.jackson.databind.JsonNode;
import models.main.Reporte;
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

public class ReporteControllerTest {

    private static final String UTF_8 = "utf-8";



    @Test
    public void testCreate()
    {

        running(fakeApplication(), ()->{
            Reporte reporte =new Reporte();
            reporte.setId(1L);
            reporte.setFecha(new Date());
            reporte.setIdNivel(1L);


            Call action = controllers.routes.ReporteController.create(reporte.getIdNivel());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(reporte)));


            assertEquals("El reporte no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del reporte no es el esperado", (long) reporte.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("La fecha no es la esperada", (long) reporte.getFecha(),json.get("fecha").asLong(),0);


        });
    }

    @Test
    public void testRetrieve()
    {
        running(fakeApplication(), ()->{
            Reporte reporte =new Reporte();
            reporte.setId(1L);
            reporte.setFecha(new Date());
            reporte.setIdNivel(1L);


            Call action = controllers.routes.AlertaController.create(reporte.getIdNivel());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(reporte)));
            Result result = route(Helpers.fakeRequest(controllers.routes.ReporteController.retrieve(reporte.getId())));

            assertEquals("El reporte no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del reporte no es el esperado", (long) reporte.getId(), json.get("id").asLong());
            //assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("La fecha no es la esperada", (long) reporte.getFecha(),json.get("fecha").asLong(),0);

        });


    }

    @Test
    public void testRetrieveAll()
    {
        running(fakeApplication(), ()-> {
            int cant = 10;
            List<Reporte> reportes=new ArrayList<>();
            for (int i = 0; i < cant; i++) {
                Reporte reporte =new Reporte();
                reporte.setId((long)i);
                reporte.setFecha(new Date());
                reporte.setIdNivel((long)i);
                reportes.add(reporte);

                Call action = controllers.routes.AlertaController.create(reporte.getIdNivel());
                Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(reporte)));

            }

            for (int i = 0; i < cant; i++) {
                Reporte reporte = reportes.get(i);
                Result result =route(Helpers.fakeRequest(controllers.routes.ReporteController.retrieve(reporte.getId())));

                assertEquals("El reporte no fue creado correctamente.", Http.Status.OK, result.status());
                assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                assertEquals("El id del reporte no es el esperado", (long) reporte.getId(), json.get("id").asLong());

                assertEquals("La fecha no es la esperada", (long) reporte.getFecha(),json.get("fecha").asLong(),0);
            }

        }


    }

    @Test
    public void testUpdate()
    {

        running(fakeApplication(), ()->{
            Reporte reporte =new Reporte();
            reporte.setId(1L);
            reporte.setFecha(new Date());
            reporte.setIdNivel(1L);

            Call action = controllers.routes.ReporteController.create(reporte.getIdNivel());
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(reporte)));

            reporte.setId(2L);
            reporte.setFecha(new Date());
            reporte.setIdNivel(2L);

            action = controllers.routes.AlertaController.update(reporte.getId());
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(reporte)));
            assertEquals("El reporte no fue creado correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());


            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id del reporte no es el esperado", (long) reporte.getId(), json.get("id").asLong());

            assertEquals("La fecha no es la esperada", (long) reporte.getFecha(),json.get("fecha").asLong(),0);

            assertEquals("El id del reporte no es el esperado", (long) reporte.getId(), json.get("id").asLong());

            assertEquals("La fecha no es la esperada", (long) reporte.getFecha(),json.get("fecha").asLong(),0);

        });

    }

    @Test
    public void testDelete()
    {

    }
}
