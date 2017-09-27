package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Dato;
import org.junit.Before;
import org.junit.Test;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatoControllerTest {

    private static final String UTF_8 = "utf-8";
    private DatoController controller;

    @Before
    public void setup() {
        controller = new DatoController();
    }

    @Test
    public void testCreate() {
        Dato dato = new Dato();
        dato.setId(10L);
        dato.setValor(20f);
        dato.setIdSensor(30L);

        Result result = controller.create(dato.getIdSensor());
        assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (double) dato.getValor(), json.get("valor").asDouble(), 0);
        assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
    }

    @Test
    public void testRetrieve() {
        Dato dato = new Dato();
        dato.setId(10L);
        dato.setValor(20f);
        dato.setIdSensor(30L);

        controller.create(dato.getIdSensor());
        Result result = controller.retrieve(dato.getId());

        assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (double) dato.getValor(), json.get("valor").asDouble(), 0);
        assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
    }

    @Test
    public void testRetrieveAll() {
        List<Dato> datos = new ArrayList<>();

        int cant = 10;
        for (int i = 0; i < cant; i++) {
            Dato dato = new Dato();
            dato.setId((long) i);
            dato.setValor((float) i);
            dato.setIdSensor((long) i);
            datos.add(dato);
            controller.create(dato.getIdSensor());
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
            assertEquals("El valor del dato no es el esperado", (double) dato.getValor(), json.get("valor").asDouble(), 0);
            assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
        }
    }

    @Test
    public void testUpdate() {
        Dato dato = new Dato();
        dato.setId(10L);
        dato.setValor(20f);
        dato.setIdSensor(30L);
        controller.create(dato.getIdSensor());

        dato.setId(50L);
        dato.setValor(60f);
        dato.setIdSensor(70L);

        Result result = controller.update(dato.getId());

        assertEquals("El dato no fue creado correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (double) dato.getValor(), json.get("valor").asDouble(), 0);
        assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());

        assertEquals("El id del dato no es el esperado", (long) dato.getId(), json.get("id").asLong());
        assertEquals("El valor del dato no es el esperado", (double) dato.getValor(), json.get("valor").asDouble(), 0);
        assertEquals("El id del sensor del dato no es el esperado", (long) dato.getIdSensor(), json.get("idSensor").asLong());
    }
}
