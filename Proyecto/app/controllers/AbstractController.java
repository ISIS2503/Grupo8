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
    private ObjectMapper mapper = new ObjectMapper();

    public T jsonToObject(JsonNode node)
    {
        try
        {
            T obj = (T) new Object();
            return mapper.treeToValue(node, (Class<T>) obj.getClass());
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
