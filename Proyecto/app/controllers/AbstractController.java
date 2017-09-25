package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Controller;

/**
 * Created by juanchaves on 24/09/17.
 */
public class AbstractController<T> extends Controller
{
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T jsonToObject(JsonNode node, Class<T> clase)
    {
        try
        {
            return mapper.treeToValue(node, clase);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
