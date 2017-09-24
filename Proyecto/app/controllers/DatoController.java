package controllers;

import models.com.arquisix.sistemaminas.entities.Dato;
import models.com.arquisix.sistemaminas.logic.DatoLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by juanchaves on 24/09/17.
 */
public class DatoController extends AbstractController<Dato>
{
    @Inject
    private DatoLogic logic;

    public Result create(Long idSensor)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idSensor, jsonToObject(json))));
    }

    public Result retrieveAll(Long idSensor)
    {
        return ok(Json.toJson(logic.retrieveAll(idSensor)));
    }

    public Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public Result update(Long idSensor, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idSensor, id, jsonToObject(json))));
    }

    public Result delete(Long idSensor, Long idDato)
    {
        return ok(Json.toJson(logic.delete(idSensor, idDato)));
    }
}
