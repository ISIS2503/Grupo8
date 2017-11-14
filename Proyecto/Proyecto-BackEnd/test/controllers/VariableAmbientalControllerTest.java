package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.VariableAmbiental;
import play.core.j.JavaResultExtractor;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;

import java.util.ArrayList;
import java.util.List;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

/**
 * Created by s.cortes on 27/09/2017.
 */
public class VariableAmbientalControllerTest {
    private static final String UTF_8 = "utf-8";

    //@Test
    public void testCreate() {

        running(fakeApplication(), ()->{

            VariableAmbiental variableAmbiental = new VariableAmbiental();
            variableAmbiental.setId(10L);
            variableAmbiental.setFrecuencia(1f);
            variableAmbiental.setPrecision(1f);
            variableAmbiental.setUniadadDeMedida("Unidad");
            variableAmbiental.setValorMaximo(3f);
            variableAmbiental.setValorMinimo(1f);
            variableAmbiental.setVariacion(0.5f);

            Call action = controllers.routes.VariableAmbientalController.create(); //TODO controllers.
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(variableAmbiental)));

            //assertEquals("El variableAmbiental no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id de la variable Ambiental no es el esperado", (long) variableAmbiental.getId(), json.get("id").asLong());
            //assertEquals("La frecuencia de la variable Ambiental no es el esperado", (float) variableAmbiental.getFrecuencia(), json.get("frecuencia").floatValue());
            //assertEquals("La precision de la variable Ambiental no es el esperado", (float) variableAmbiental.getPrecision(), json.get("precision").floatValue());
            //assertEquals("La unidad de medida de la variable Ambiental no es el esperado", variableAmbiental.getUniadadDeMedida(), json.get("uniadadDeMedida").asText());
            //assertEquals("El valor maximo de la variable Ambiental no es el esperado", (float) variableAmbiental.getValorMaximo(), json.get("valorMaximo").floatValue());
        });

    }

    //@Test
    public void testRetrieve() {
        running(fakeApplication(), ()->{

            VariableAmbiental variableAmbiental = new VariableAmbiental();
            variableAmbiental.setId(10L);
            variableAmbiental.setFrecuencia(1f);
            variableAmbiental.setPrecision(1f);
            variableAmbiental.setUniadadDeMedida("Unidad");
            variableAmbiental.setValorMaximo(3f);
            variableAmbiental.setValorMinimo(1f);
            variableAmbiental.setVariacion(0.5f);

            Call action = controllers.routes.VariableAmbientalController.create(); //TODO controllers.
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(variableAmbiental)));
            Result result = route(Helpers.fakeRequest(controllers.routes.VariableAmbientalController.retrieve(variableAmbiental.getId())));

            //assertEquals("El variableAmbiental no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id de la variable Ambiental no es el esperado", (long) variableAmbiental.getId(), json.get("id").asLong());
            //assertEquals("La frecuencia de la variable Ambiental no es el esperado", (float) variableAmbiental.getFrecuencia(), json.get("frecuencia").floatValue());
            //assertEquals("La precision de la variable Ambiental no es el esperado", (float) variableAmbiental.getPrecision(), json.get("precision").floatValue());
            //assertEquals("La unidad de medida de la variable Ambiental no es el esperado", variableAmbiental.getUniadadDeMedida(), json.get("uniadadDeMedida").asText());
            //assertEquals("El valor maximo de la variable Ambiental no es el esperado", (float) variableAmbiental.getValorMaximo(), json.get("valorMaximo").floatValue());
        });
    }

    //@Test
    public void testRetrieveAll() {
        running(fakeApplication(), ()-> {
            int cant = 10;
            List<VariableAmbiental> variables = new ArrayList<>();

            for (int i = 0; i < cant; i++) {
                VariableAmbiental variableAmbiental = new VariableAmbiental();
                variableAmbiental.setId((long)i);
                variableAmbiental.setFrecuencia(1f+i);
                variableAmbiental.setPrecision(1f+i);
                variableAmbiental.setUniadadDeMedida("Unidad"+i);
                variableAmbiental.setValorMaximo(3f+i);
                variableAmbiental.setValorMinimo(1f+i);
                variableAmbiental.setVariacion(0.5f+i);

                Call action = controllers.routes.VariableAmbientalController.create();
                Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(variableAmbiental)));
            }
            for (int i = 0; i < cant; i++) {
                VariableAmbiental variableAmbiental = variables.get(i);
                Result result = route(Helpers.fakeRequest(controllers.routes.VariableAmbientalController.retrieve(variableAmbiental.getId())));

                //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
                //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
                //assertEquals(UTF_8, result.charset());

                byte[] body = JavaResultExtractor.getBody(result, 0L);
                JsonNode json = Json.parse(body);

                //assertEquals("El id de la variable Ambiental no es el esperado", (long) variableAmbiental.getId(), json.get("id").asLong());
                //assertEquals("La frecuencia de la variable Ambiental no es el esperado", (float) variableAmbiental.getFrecuencia(), json.get("frecuencia").floatValue());
                //assertEquals("La precision de la variable Ambiental no es el esperado", (float) variableAmbiental.getPrecision(), json.get("precision").floatValue());
                //assertEquals("La unidad de medida de la variable Ambiental no es el esperado", variableAmbiental.getUniadadDeMedida(), json.get("uniadadDeMedida").asText());
                //assertEquals("El valor maximo de la variable Ambiental no es el esperado", (float) variableAmbiental.getValorMaximo(), json.get("valorMaximo").floatValue());
            }
        });
    }

    //@Test
    public void testUpdate( ){
        running(fakeApplication(), ()-> {
            VariableAmbiental variableAmbiental = new VariableAmbiental();
            variableAmbiental.setId(10L);
            variableAmbiental.setFrecuencia(1f);
            variableAmbiental.setPrecision(1f);
            variableAmbiental.setUniadadDeMedida("Unidad");
            variableAmbiental.setValorMaximo(3f);
            variableAmbiental.setValorMinimo(1f);
            variableAmbiental.setVariacion(0.5f);

            Call action = controllers.routes.VariableAmbientalController.create();
            Result result1 = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(variableAmbiental)));

            variableAmbiental.setId(10L);
            variableAmbiental.setFrecuencia(1f);
            variableAmbiental.setPrecision(1f);
            variableAmbiental.setUniadadDeMedida("Unidad");
            variableAmbiental.setValorMaximo(3f);
            variableAmbiental.setValorMinimo(1f);
            variableAmbiental.setVariacion(0.5f);

            action = routes.VariableAmbientalController.update(variableAmbiental.getId()); // TODO controllers.
            Result result = route(Helpers.fakeRequest(action).bodyJson(Json.toJson(variableAmbiental)));

            //assertEquals("El usuario no fue creado correctamente.", Http.Status.OK, result.status());
            //assertEquals("El resultado no es del tipo esperado", "application/json", result.contentType());
            //assertEquals(UTF_8, result.charset());

            byte[] body = JavaResultExtractor.getBody(result, 0L);
            JsonNode json = Json.parse(body);

            //assertEquals("El id de la variable Ambiental no es el esperado", (long) variableAmbiental.getId(), json.get("id").asLong());
            //assertEquals("La frecuencia de la variable Ambiental no es el esperado", (float) variableAmbiental.getFrecuencia(), json.get("frecuencia").floatValue());
            //assertEquals("La precision de la variable Ambiental no es el esperado", (float) variableAmbiental.getPrecision(), json.get("precision").floatValue());
            //assertEquals("La unidad de medida de la variable Ambiental no es el esperado", variableAmbiental.getUniadadDeMedida(), json.get("uniadadDeMedida").asText());
            //assertEquals("El valor maximo de la variable Ambiental no es el esperado", (float) variableAmbiental.getValorMaximo(), json.get("valorMaximo").floatValue());
        });
    }
}
