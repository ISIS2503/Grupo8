package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Nivel;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class NivelController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		Nivel nivel = Nivel.bind( json );
		//TODO
		nivel.save( );
		return ok( Json.toJson( nivel ) );
	}

	public Result retrieveAll( )
	{
		List<Nivel> nivel = new Model.Finder<Long, Nivel>( Nivel.class ).findList( );
		return ok( Json.toJson( nivel ) );
	}

	public Result retrieve( Long id )
	{
		Nivel nivel = new Model.Finder<Long, Nivel>( Nivel.class ).byId( id );
		return ok( Json.toJson( nivel ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Nivel nivel = Nivel.bind( json );
		nivel.setId( id );
		nivel.save( );
		return ok( Json.toJson( nivel ) );
	}

	public Result delete( Long id )
	{
		Nivel nivel = new Model.Finder<Long, Nivel>( Nivel.class ).byId( id );
		nivel.delete( );
		return ok( Json.toJson( nivel ) );
	}
}
