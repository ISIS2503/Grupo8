package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class UsuarioController extends Controller
{
	@BodyParser.Of( BodyParser.Json.class )
	public play.mvc.Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		Usuario usuario = Usuario.bind( json );
		usuario.save( );
		return ok( Json.toJson( usuario ) );
	}

	public Result retrieveAll( )
	{
		List<Usuario> usuario = new Model.Finder<Long, Usuario>( Usuario.class ).findList( );
		return ok( Json.toJson( usuario ) );
	}

	public Result retrieve( Long id )
	{
		Usuario usuario = new Model.Finder<Long, Usuario>( Usuario.class ).byId( id );
		return ok( Json.toJson( usuario ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Usuario usuario = Usuario.bind( json );
		usuario.setId( id );
		usuario.save( );
		return ok( Json.toJson( usuario ) );
	}

	public Result delete( Long id )
	{
		Usuario usuario = new com.avaje.ebean.Model.Finder<Long, Usuario>( Usuario.class ).byId( id );
		usuario.delete( );
		return ok( play.libs.Json.toJson( usuario ) );
	}
}
