package controllers;

import models.com.arquisix.sistemaminas.entities.VariableAmbiental;
import models.com.arquisix.sistemaminas.logic.VariableAmbientalLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

public class VariableAmbientalController extends AbstractController<VariableAmbiental>
{
    private static VariableAmbientalLogic logic = new VariableAmbientalLogic();

    public static Result create()
    {

        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(jsonToObject(json, VariableAmbiental.class))));
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
        return ok(Json.toJson(logic.update(id, jsonToObject(json, VariableAmbiental.class))));
    }

    public static Result delete(Long id)
    {
        return ok(Json.toJson(logic.delete(id)));
    }
}
