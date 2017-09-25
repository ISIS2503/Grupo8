package controllers;

import models.com.arquisix.sistemaminas.entities.Actuador;
import models.com.arquisix.sistemaminas.logic.ActuadorLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

public class ActuadorController extends AbstractController<Actuador>
{
    private static ActuadorLogic logic = new ActuadorLogic();

    public static Result create(Long idArea)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idArea, jsonToObject(json, Actuador.class))));
    }

    public static Result retrieveAll(Long idArea)
    {
        return ok(Json.toJson(logic.retrieveAll(idArea)));
    }

    public static Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public static Result update(Long idArea, Long id)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(idArea, id, jsonToObject(json, Actuador.class))));
    }

    public static Result delete(Long idArea, Long idActuador)
    {
        return ok(Json.toJson(logic.delete(idArea, idActuador)));
    }
}
