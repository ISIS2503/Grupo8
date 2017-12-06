package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.users.Usuario;
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
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		if( Usuario.find.where( ).eq( "login", json.findPath( "login" ).asText( ) ).findUnique( ) != null )
		{
			return forbidden( "Ya existe un usuario con el login dado" );
		}
		Usuario usuario = Usuario.bind( json );
		usuario.setId( null );
		usuario.insert( "usersdb" );
		return ok( Json.toJson( Usuario.find.byId( usuario.getId( ) ) ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( )
	{
		List<Usuario> usuarios = Usuario.find.findList( );
		usuarios.forEach( usuario -> usuario.setPassword( null ) );
		return ok( Json.toJson( usuarios ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
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

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Usuario usuario = Usuario.bind( json );
		usuario.setId( id );
		usuario.setPassword( usuario.getPassword( ) != null ? usuario.getPassword( ) : Usuario.find.byId( id ).getPassword( ) );
		usuario.update( "usersdb" );
		return ok( Json.toJson( usuario ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Usuario usuario = Usuario.find.byId( id );
		usuario.delete( "usersdb" );
		return ok( play.libs.Json.toJson( usuario ) );
	}
}
