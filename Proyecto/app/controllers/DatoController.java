package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Dato;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class DatoController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idSensor )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		dato.setIdSensor( idSensor );
		dato.save( );
		return ok( Json.toJson( dato ) );
	}

	public Result retrieveAll( Long idSensor )
	{
		List<Dato> dato = new Model.Finder<Long, Dato>( Dato.class ).where( ).eq( "idSensor", idSensor ).findList( );
		return ok( Json.toJson( dato ) );
	}

	public Result retrieve( Long id )
	{
		Dato dato = new Model.Finder<Long, Dato>( Dato.class ).byId( id );
		return ok( Json.toJson( dato ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Dato dato = Dato.bind( json );
		dato.setId( id );
		dato.save( );
		return ok( Json.toJson( dato ) );
	}

	public Result delete( Long id )
	{
		Dato dato = new Model.Finder<Long, Dato>( Dato.class ).byId( id );
		dato.delete( );
		return ok( Json.toJson( dato ) );
	}
}
