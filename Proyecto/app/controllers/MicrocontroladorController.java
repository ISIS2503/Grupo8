package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Microcontrolador;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class MicrocontroladorController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		microcontrolador.setIdArea( idArea );
		microcontrolador.save( );
		return ok( Json.toJson( microcontrolador ) );
	}

	public Result retrieveAll( Long idArea )
	{
		List<Microcontrolador> microcontrolador = new Model.Finder<Long, Microcontrolador>( Microcontrolador.class ).where( ).eq( "idArea", idArea ).findList( );
		return ok( Json.toJson( microcontrolador ) );
	}

	public Result retrieve( Long id )
	{
		Microcontrolador microcontrolador = new Model.Finder<Long, Microcontrolador>( Microcontrolador.class ).byId( id );
		return ok( Json.toJson( microcontrolador ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		microcontrolador.setId( id );
		microcontrolador.save( );
		return ok( Json.toJson( microcontrolador ) );
	}

	public Result delete( Long id )
	{
		Microcontrolador microcontrolador = new Model.Finder<Long, Microcontrolador>( Microcontrolador.class ).byId( id );
		microcontrolador.delete( );
		return ok( play.libs.Json.toJson( microcontrolador ) );
	}
}
