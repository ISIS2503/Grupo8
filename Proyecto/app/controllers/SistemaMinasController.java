package controllers;

import models.com.arquisix.sistemaminas.entities.SistemaMinas;
import models.com.arquisix.sistemaminas.logic.SistemaMinasLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

public class SistemaMinasController extends AbstractController<SistemaMinas>
{
    private static SistemaMinasLogic logic = new SistemaMinasLogic();

    public static Result create()
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(jsonToObject(json, SistemaMinas.class))));
    }

    public static Result retrieveAll()
    {
        return ok(Json.toJson(logic.retrieveAll()));
    }

    public static Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public static Result update(Long id)
    {

        JsonNode json = request().body().asJson();

        return ok(Json.toJson(logic.update(id, jsonToObject(json, SistemaMinas.class))));
    }

    public static Result delete(Long id)
    {
        return ok(Json.toJson(logic.delete(id)));
    }
}
