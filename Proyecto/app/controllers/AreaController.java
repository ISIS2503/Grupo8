package controllers;

import models.com.arquisix.sistemaminas.entities.Area;
import models.com.arquisix.sistemaminas.logic.AreaLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

public class AreaController extends AbstractController<Area>
{
    private static AreaLogic logic = new AreaLogic();

    public static Result create(Long idNivel)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idNivel, jsonToObject(json, Area.class))));
    }

    public static Result retrieveAll(Long idNivel)
    {
        return ok(Json.toJson(logic.retrieveAll(idNivel)));
    }

    public static Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public static Result update(Long idNivel, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idNivel, id, jsonToObject(json, Area.class))));
    }

    public static Result delete(Long idNivel, Long idArea)
    {
        return ok(Json.toJson(logic.delete(idNivel, idArea)));
    }
}
