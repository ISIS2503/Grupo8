package controllers;

import com.avaje.ebean.Model;
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
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( )
	{
		JsonNode json = request( ).body( ).asJson( );
		VariableAmbiental variableAmbiental = VariableAmbiental.bind( json );
		variableAmbiental.save( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	public Result retrieveAll( )
	{
		List<VariableAmbiental> variableAmbiental = new Model.Finder<Long, VariableAmbiental>( VariableAmbiental.class ).findList( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	public Result retrieve( Long id )
	{
		VariableAmbiental variableAmbiental = new Model.Finder<Long, VariableAmbiental>( VariableAmbiental.class ).byId( id );
		return ok( Json.toJson( variableAmbiental ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		VariableAmbiental variableAmbiental = VariableAmbiental.bind( json );
		variableAmbiental.setId( id );
		variableAmbiental.save( );
		return ok( Json.toJson( variableAmbiental ) );
	}

	public Result delete( Long id )
	{
		VariableAmbiental variableAmbiental = new Model.Finder<Long, VariableAmbiental>( VariableAmbiental.class ).byId( id );
		variableAmbiental.delete( );
		return ok( Json.toJson( variableAmbiental ) );
	}
}
