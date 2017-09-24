package controllers;

import models.com.arquisix.sistemaminas.entities.Actuador;
import models.com.arquisix.sistemaminas.logic.ActuadorLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by juanchaves on 24/09/17.
 */
public class ActuadorController extends AbstractController<Actuador>
{
    @Inject
    private ActuadorLogic logic;

    public Result create(Long idArea)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idArea, jsonToObject(json))));
    }

    public Result retrieveAll(Long idArea)
    {
        return ok(Json.toJson(logic.retrieveAll(idArea)));
    }

    public Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public Result update(Long idArea, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idArea, id, jsonToObject(json))));
    }

    public Result delete(Long idArea, Long idActuador)
    {
        return ok(Json.toJson(logic.delete(idArea, idActuador)));
    }
}
