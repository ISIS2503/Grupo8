package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import models.main.Alerta;
import models.main.Nivel;
import org.junit.Before;
import org.junit.Test;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class NivelControllerTest {

    private static final String UTF_8 = "utf-8";
    private NivelController controller;

    @Before
    public void setup() {
        controller = new NivelController();
    }

    @Test
    public void testCreate()
    {
        Nivel nivel =new Nivel();
        nivel.setId(10L);
        nivel.setNivel(20);


        Result result = controller.create();


        assertEquals("La nivel no fue creado correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());


        byte[] body = JavaResultExtractor.getBody(result, 0L);

        JsonNode json = Json.parse(body);

        assertEquals("El id del nivel no es el esperado", (long) nivel.getId(), json.get("id").asLong());
        assertEquals("El nivel no es el esperado", (int) nivel.getNivel(), json.get("nivel").asInt());
    }

    @Test
    public void testRetrieve() {
        Nivel nivel =new Nivel();
        nivel.setId(10L);
        nivel.setNivel(20);

        controller.create();
        Result result = controller.retrieve(nivel.getId());

        assertEquals("La nivel no fue creada correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id del nivel no es el esperado", (long) nivel.getId(), json.get("id").asLong());
        assertEquals("El numero del nivel no es el esperado", (long) nivel.getId(), json.get("nivel").asInt());
    }

    @Test
    public void testRetrieveAll() {

        List<Alerta> alertas=new ArrayList<>();

        int cant = 10;
        for (int i = 0; i < cant; i++) {
            Nivel nivel =new Nivel();
            nivel.setId((long)i);
            nivel.setNivel(i);
            alertas.add(nivel);
            controller.create(nivel.getId());
        }

        for (int i = 0; i < cant; i++) {
            Nivel nivel =alertas.get(i);
            Result result = controller.retrieve(nivel.getId());

            assertEquals("La nivel no fue creada correctamente.", Http.Status.OK, result.status());
            assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            assertEquals("El id de la nivel no es la esperada", (long) nivel.getId(), json.get("id").asLong());
            assertEquals("El numero del nivel no es el esperado", nivel.getNivel(), json.get("nivel").asInt());
        }
    }

    @Test
    public void testUpdate() {

        Nivel nivel =new Nivel();
        nivel.setId(10L);
        nivel.setNivel(20);
        controller.create(nivel.getId());

        nivel.setId(30L);
        nivel.setNivel(40L);
        Result result = controller.update(nivel.getId());

        assertEquals("La nivel no fue creada correctamente.", Http.Status.OK, result.status());
        assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
        assertEquals(UTF_8, result.charset());

        byte[] body = JavaResultExtractor.getBody(result, 0L);
        JsonNode json = Json.parse(body);

        assertEquals("El id de la nivel no es la esperada", (long) nivel.getId(), json.get("id").asLong());
        assertEquals("El area de la nivel no es la esperada", nivel.getIdArea(), json.get("nivel").asInt());

        assertEquals("El id de la nivel no es el esperado", (long) nivel.getId(), json.get("id").asLong());
        assertEquals("El numero del nivel no es el esperado", nivel.getIdArea(), json.get("nivel").asInt());
    }
}