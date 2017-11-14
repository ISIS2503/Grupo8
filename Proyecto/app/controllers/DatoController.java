package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.Dato;
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
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idSensor )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		dato.setIdSensor( idSensor );
		dato.save( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( Long idSensor )
	{
		List<Dato> dato = Dato.find.where( ).eq( "idSensor", idSensor ).findList( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		Dato dato = Dato.find.byId( id );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		dato.setId( id );
		dato.save( );
		return ok( Json.toJson( dato ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		Dato dato = Dato.find.byId( id );
		dato.delete( );
		return ok( Json.toJson( dato ) );
	}
}
