package controllers;

import actions.Roles;
import actions.RolesAllowed;
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
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		Usuario usuario = Usuario.bind( json );
		usuario.save( );
		return ok( Json.toJson( usuario ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( )
	{
		List<Usuario> usuarios = Usuario.find.findList( );
		usuarios.forEach( usuario -> usuario.setPassword( null ) );
		return ok( Json.toJson( usuarios ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		Usuario usuario = Usuario.find.byId( id );
		if( usuario != null )
		{
			usuario.setPassword( null );
			return ok( Json.toJson( usuario ) );
		}
		return notFound( String.format( "El usuario con id %s no se encuentra en la base de datos", id ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Usuario usuario = Usuario.bind( json );
		usuario.setId( id );
		usuario.save( );
		return ok( Json.toJson( usuario ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		Usuario usuario = new com.avaje.ebean.Model.Finder<Long, Usuario>( Usuario.class ).byId( id );
		usuario.delete( );
		return ok( play.libs.Json.toJson( usuario ) );
	}
}
