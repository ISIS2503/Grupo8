package controllers;

import models.com.arquisix.sistemaminas.entities.Area;
import models.com.arquisix.sistemaminas.logic.AreaLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by juanchaves on 24/09/17.
 */
public class AreaController extends AbstractController<Area>
{
    @Inject
    private AreaLogic logic;

    public Result create(Long idNivel)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idNivel, jsonToObject(json))));
    }

    public Result retrieveAll(Long idNivel)
    {
        return ok(Json.toJson(logic.retrieveAll(idNivel)));
    }

    public Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public Result update(Long idNivel, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idNivel, id, jsonToObject(json))));
    }

    public Result delete(Long idNivel, Long idArea)
    {
        return ok(Json.toJson(logic.delete(idNivel, idArea)));
    }
}
