package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class SensorController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idMicrocontrolador )
	{
		JsonNode json = request( ).body( ).asJson( );
		Sensor sensor = Sensor.bind( json );
		sensor.setIdMicrocontrolador( idMicrocontrolador );
		sensor.save( );
		return ok( Json.toJson( sensor ) );
	}

	public Result retrieveAll( Long idMicrocontrolador )
	{
		List<Sensor> sensor = new Model.Finder<Long, Sensor>( Sensor.class ).where( ).eq( "idMicrocontrolador", idMicrocontrolador ).findList( );
		return ok( Json.toJson( sensor ) );
	}

	public Result retrieve( Long id )
	{
		Sensor sensor = new Model.Finder<Long, Sensor>( Sensor.class ).byId( id );
		return ok( Json.toJson( sensor ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Sensor sensor = Sensor.bind( json );
		sensor.setId( id );
		sensor.save( );
		return ok( Json.toJson( sensor ) );
	}

	public Result delete( Long id )
	{
		Sensor sensor = new Model.Finder<Long, Sensor>( Sensor.class ).byId( id );
		sensor.delete( );
		return ok( Json.toJson( sensor ) );
	}
}
