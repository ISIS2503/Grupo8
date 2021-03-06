package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Area;
import models.main.Nivel;
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
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idNivel )
	{
		com.fasterxml.jackson.databind.JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		Nivel nivel = new Nivel( );
		nivel.setId( idNivel );
		area.setNivel( nivel );
		area.save( );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public play.mvc.Result retrieveAll( Long idNivel )
	{
		List<Area> area = Area.find.where( ).eq( "nivel.id", idNivel ).findList( );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public play.mvc.Result retrieve( Long id )
	{
		Area area = Area.find.byId( id );
		return ok( Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		area.setId( id );
		area.update( );
		return ok( play.libs.Json.toJson( area ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public play.mvc.Result delete( Long id )
	{
		Area area = Area.find.byId( id );
		area.delete( );
		return ok( Json.toJson( area ) );
	}
}
