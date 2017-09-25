package controllers;

import models.com.arquisix.sistemaminas.entities.Nivel;
import models.com.arquisix.sistemaminas.logic.NivelLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;


public class NivelController extends AbstractController<Nivel>
{
    private static NivelLogic logic = new NivelLogic();

    public static Result create() throws JsonProcessingException
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson( logic.create( jsonToObject(json, Nivel.class) ) ));
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
        return ok(Json.toJson(logic.update(id, jsonToObject(json, Nivel.class))));
    }

    public static Result delete(Long id)
    {
        return ok(Json.toJson(logic.delete(id)));
    }
}
