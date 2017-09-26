package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Alerta;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class AlertaController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Alerta alerta = Alerta.bind( json );
		//TODO
		alerta.save( );
		return ok( Json.toJson( alerta ) );
	}

	public Result retrieveAll( Long idArea )
	{
		List<Alerta> alerta = new Model.Finder<Long, Alerta>( Alerta.class ).where( ).eq( "idArea", idArea ).findList( );
		return ok( Json.toJson( alerta ) );
	}

	public Result retrieve( Long id )
	{
		Alerta alerta = new Model.Finder<Long, Alerta>( Alerta.class ).byId( id );
		return ok( Json.toJson( alerta ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Alerta alerta = Alerta.bind( json );
		alerta.setId( id );
		alerta.save( );
		return ok( Json.toJson( alerta ) );
	}

	public Result delete( Long id )
	{
		Alerta alerta = new Model.Finder<Long, Alerta>( Alerta.class ).byId( id );
		alerta.delete( );
		return ok( Json.toJson( alerta ) );
	}
}
