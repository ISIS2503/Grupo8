package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.users.Rol;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class RolController extends Controller
{
	@RolesAllowed( { Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		Rol rol = Rol.bind( json );
		rol.insert( "usersdb" );
		return ok( Json.toJson( rol ) );
	}

	// @RolesAllowed( { Roles.ADMIN, Roles.SYSO, Roles.USER } )
	public Result retrieveAll( )
	{
		List<Rol> roles = Rol.find.findList( );
		return ok( Json.toJson( roles ) );
	}

	@RolesAllowed( { Roles.SYSO } )
	public Result retrieve( Long id )
	{
		Rol rol = Rol.find.byId( id );
		return ok( Json.toJson( rol ) );
	}

	@RolesAllowed( { Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Rol rol = Rol.bind( json );
		rol.setId( id );
		rol.update( "usersdb" );
		return ok( Json.toJson( rol ) );
	}

	@RolesAllowed( { Roles.SYSO } )
	public Result delete( Long id )
	{
		Rol rol = Rol.find.byId( id );
		rol.delete( "usersdb" );
		return ok( Json.toJson( rol ) );
	}
}
