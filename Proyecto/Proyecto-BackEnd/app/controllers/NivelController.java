package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Nivel;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class NivelController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		Nivel nivel = Nivel.bind( json );
		nivel.save( );
		return ok( Json.toJson( nivel ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( )
	{
		List<Nivel> nivel = Nivel.find.findList( );
		return ok( Json.toJson( nivel ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieve( Long id )
	{
		Nivel nivel = Nivel.find.byId( id );
		return ok( Json.toJson( nivel ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Nivel nivel = Nivel.bind( json );
		nivel.setId( id );
		nivel.update( );
		return ok( Json.toJson( nivel ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Nivel nivel = Nivel.find.byId( id );
		nivel.delete( );
		return ok( Json.toJson( nivel ) );
	}
}
