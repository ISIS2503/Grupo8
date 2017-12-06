package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Dato;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class DatoController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.BRIDGE } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idSensor )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		models.main.Sensor sensor = new models.main.Sensor( );
		sensor.setId( idSensor );
		dato.setSensor( sensor );
		dato.save( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( Long idSensor )
	{
		List<Dato> dato = Dato.find.where( ).eq( "sensor.id", idSensor ).findList( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieve( Long id )
	{
		Dato dato = Dato.find.byId( id );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		dato.setId( id );
		dato.update( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Dato dato = Dato.find.byId( id );
		dato.delete( );
		return ok( Json.toJson( dato ) );
	}
}
