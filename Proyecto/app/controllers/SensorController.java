package controllers;

import models.com.arquisix.sistemaminas.entities.Sensor;
import models.com.arquisix.sistemaminas.logic.SensorLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by juanchaves on 24/09/17.
 */
public class SensorController extends AbstractController<Sensor> {
    @Inject
    private SensorLogic logic;

    public Result create(Long idMicrocontrolador)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idMicrocontrolador, jsonToObject(json))));
    }

    public Result retrieveAll(Long idMicrocontrolador) {
        return ok(Json.toJson(logic.retrieveAll(idMicrocontrolador)));
    }

    public Result retrieve(Long id) {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public Result update(Long idMicrocontrolador,Long id) {


        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idMicrocontrolador, id, jsonToObject(json))));
    }

    public Result delete(Long idMicrocontrolador, Long idSensor) {
        return ok(Json.toJson(logic.delete(idMicrocontrolador, idSensor)));
    }
}
