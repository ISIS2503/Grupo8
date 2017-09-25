package controllers;

import models.com.arquisix.sistemaminas.entities.Sensor;
import models.com.arquisix.sistemaminas.logic.SensorLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

public class SensorController extends AbstractController<Sensor>
{
    private static  SensorLogic logic = new SensorLogic();

    public static Result create(Long idMicrocontrolador)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idMicrocontrolador, jsonToObject(json, Sensor.class))));
    }

    public static Result retrieveAll(Long idMicrocontrolador)
    {
        return ok(Json.toJson(logic.retrieveAll(idMicrocontrolador)));
    }

    public static Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public static Result update(Long idMicrocontrolador, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idMicrocontrolador, id, jsonToObject(json, Sensor.class))));
    }

    public static Result delete(Long idMicrocontrolador, Long idSensor)
    {
        return ok(Json.toJson(logic.delete(idMicrocontrolador, idSensor)));
    }
}
