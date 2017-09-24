package controllers;

import models.com.arquisix.sistemaminas.entities.VariableAmbiental;
import models.com.arquisix.sistemaminas.logic.VariableAmbientalLogic;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by juanchaves on 24/09/17.
 */
public class VariableAmbientalController  extends AbstractController<VariableAmbiental>
{
    @Inject
    private VariableAmbientalLogic logic;

    public Result create()
    {

        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.create(jsonToObject(json))));
    }

    public Result retrieveAll()
    {
        return ok(Json.toJson(logic.retrieveAll()));
    }

    public Result retrieve(Long id)
    {
        return ok(Json.toJson(logic.retrieve(id)));
    }

    public Result update(Long id)
    {


        JsonNode json = request().body().asJson();
        return ok(Json.toJson(logic.update(id, jsonToObject(json))));
    }

    public Result delete(Long id)
    {
        return ok(Json.toJson(logic.delete(id)));
    }
}
