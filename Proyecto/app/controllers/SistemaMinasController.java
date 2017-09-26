package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.SistemaMinas;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class SistemaMinasController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		SistemaMinas sistemaMinas = SistemaMinas.bind( json );
		//TODO
		sistemaMinas.save( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	public Result retrieveAll( )
	{
		List<SistemaMinas> sistemaMinas = new Model.Finder<Long, SistemaMinas>( SistemaMinas.class ).findList( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	public Result retrieve( Long id )
	{
		SistemaMinas sistemaMinas = new Model.Finder<Long, SistemaMinas>( SistemaMinas.class ).byId( id );
		return ok( Json.toJson( sistemaMinas ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		SistemaMinas sistemaMinas = SistemaMinas.bind( json );
		sistemaMinas.setId( id );
		sistemaMinas.save( );
		return ok( Json.toJson( sistemaMinas ) );
	}

	public Result delete( Long id )
	{
		SistemaMinas sistemaMinas = new Model.Finder<Long, SistemaMinas>( SistemaMinas.class ).byId( id );
		sistemaMinas.delete( );
		return ok( Json.toJson( sistemaMinas ) );
	}
}
