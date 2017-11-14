package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.Alerta;
import org.junit.Before;
import org.junit.Test;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class AlertaControllerTest {

    private static final String UTF_8 = "utf-8";
    private AlertaController controller;

    @Before
    public void setup() {
        controller = new AlertaController();
    }

    @Test
    public void testCreate()
    {
        Alerta alerta =new Alerta();
        alerta.setId(10L);
        alerta.setIdArea(20L);
        alerta.setTipo(1);

        Result result = controller.create(alerta.getId());
        assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());


        byte[] body = JavaResultExtractor.getBody(result, 0L);

        JsonNode json = Json.parse(body);

        assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
        assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
        assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);

    }

    @Test
    public void testRetrieve() {
        Alerta alerta =new Alerta();
        alerta.setId(10L);
        alerta.setIdArea(20L);
        alerta.setTipo(1);

        controller.create(alerta.getIdArea());
        Result result = controller.retrieve(alerta.getId());

        assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
        assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
        assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);
    }

    @Test
    public void testRetrieveAll() {

        List<Alerta> alertas=new ArrayList<>();

        int cant = 10;
        for (int i = 0; i < cant; i++) {
            Alerta alerta =new Alerta();
            alerta.setId((long)i);
            alerta.setIdArea((long)i);
            alerta.setTipo((int)i);
            alertas.add(alerta);
            controller.create(alerta.getIdArea());

        }

        for (int i = 0; i < cant; i++) {
            Alerta alerta =alertas.get(i);
            Result result = controller.retrieve(alerta.getId());

            assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
            assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
            assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);
        }
    }

    @Test
    public void testUpdate() {

        Alerta alerta =new Alerta();
        alerta.setId(10L);
        alerta.setIdArea(20L);
        alerta.setTipo(1);
        controller.create(alerta.getIdArea());

        alerta.setId(30L);
        alerta.setIdArea(40L);
        alerta.setTipo(2);
        Result result = controller.update(alerta.getId());

        assertEquals("La alerta no fue creada correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
        assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
        assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);

        assertEquals("El id de la alerta no es la esperada", (long) alerta.getId(), json.get("id").asLong());
        assertEquals("El area de la alerta no es la esperada", (long) alerta.getIdArea(), json.get("area").asLong());
        assertEquals("El tipo de la alerta no es la esperada", (int)alerta.getTipo(),json.get("tipo").asInt(),0);

    }

}
