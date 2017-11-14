package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.SistemaMinas;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class SistemaMinasController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		SistemaMinas sistemaMinas = SistemaMinas.bind( json );
		//TODO
		sistemaMinas.save( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( )
	{
		List<SistemaMinas> sistemaMinas = SistemaMinas.find.findList( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		SistemaMinas sistemaMinas = SistemaMinas.find.byId( id );
		return ok( Json.toJson( sistemaMinas ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		SistemaMinas sistemaMinas = SistemaMinas.bind( json );
		sistemaMinas.setId( id );
		sistemaMinas.update( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		SistemaMinas sistemaMinas = SistemaMinas.find.byId( id );
		sistemaMinas.delete( );
		return ok( Json.toJson( sistemaMinas ) );
	}
}
