package controllers;

import models.com.arquisix.sistemaminas.entities.Reporte;
import models.com.arquisix.sistemaminas.logic.ReporteLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

public class ReporteController extends AbstractController<Reporte>
{
    private static ReporteLogic logic = new ReporteLogic();

    public static Result create()
    {
        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(jsonToObject(json, Reporte.class))));
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
        return ok(Json.toJson(logic.update(id, jsonToObject(json, Reporte.class))));
    }

    public static Result delete(Long id)
    {
        return ok(Json.toJson(logic.delete(id)));
    }
}
