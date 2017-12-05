package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.VariableAmbiental;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class VariableAmbientalController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		VariableAmbiental variableAmbiental = VariableAmbiental.bind( json );
		variableAmbiental.save( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( )
	{
		List<VariableAmbiental> variableAmbiental = VariableAmbiental.find.findList( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		VariableAmbiental variableAmbiental = VariableAmbiental.find.byId( id );
		return ok( Json.toJson( variableAmbiental ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		VariableAmbiental variableAmbiental = VariableAmbiental.bind( json );
		variableAmbiental.setId( id );
		variableAmbiental.update( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		VariableAmbiental variableAmbiental = VariableAmbiental.find.byId( id );
		variableAmbiental.delete( );
		return ok( Json.toJson( variableAmbiental ) );
	}
}
