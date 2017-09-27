package controllers;

import com.avaje.ebean.Model;
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
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idNivel )
	{
		com.fasterxml.jackson.databind.JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		area.setIdNivel( idNivel );
		area.save( );
		return ok( Json.toJson( area ) );
	}

	public play.mvc.Result retrieveAll( Long idNivel )
	{
		List<Area> area = new Model.Finder<Long, Area>( Area.class ).where( ).eq( "idNivel", idNivel ).findList( );
		return ok( Json.toJson( area ) );
	}

	public play.mvc.Result retrieve( Long id )
	{
		Area area = new Model.Finder<Long, Area>( Area.class ).byId( id );
		return ok( Json.toJson( area ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Area area = Area.bind( json );
		area.setId( id );
		area.save( );
		return ok( play.libs.Json.toJson( area ) );
	}

	public play.mvc.Result delete( Long id )
	{
		Area area = new Model.Finder<Long, Area>( Area.class ).byId( id );
		area.delete( );
		return ok( Json.toJson( area ) );
	}
}
