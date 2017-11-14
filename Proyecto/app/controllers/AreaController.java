package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.Area;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class AreaController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idNivel )
	{
		com.fasterxml.jackson.databind.JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		area.setIdNivel( idNivel );
		area.save( );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public play.mvc.Result retrieveAll( Long idNivel )
	{
		List<Area> area = Area.find.where( ).eq( "idNivel", idNivel ).findList( );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public play.mvc.Result retrieve( Long id )
	{
		Area area = Area.find.byId( id );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		area.setId( id );
		area.save( );
		return ok( play.libs.Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public play.mvc.Result delete( Long id )
	{
		Area area = Area.find.byId( id );
		area.delete( );
		return ok( Json.toJson( area ) );
	}
}
