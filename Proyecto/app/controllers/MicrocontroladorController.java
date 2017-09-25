package controllers;

import models.com.arquisix.sistemaminas.entities.Microcontrolador;
import models.com.arquisix.sistemaminas.logic.MicrocontroladorLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

public class MicrocontroladorController extends AbstractController<Microcontrolador>
{
    private static MicrocontroladorLogic logic = new MicrocontroladorLogic();

    public static Result create(Long idArea)
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(idArea,jsonToObject(json, Microcontrolador.class))));
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
        return ok(Json.toJson(logic.update(idArea, id, jsonToObject(json, Microcontrolador.class))));
    }

    public static Result delete(Long idArea, Long idMicrocontrolador)
    {
        return ok(Json.toJson(logic.delete(idArea, idMicrocontrolador)));
    }
}
