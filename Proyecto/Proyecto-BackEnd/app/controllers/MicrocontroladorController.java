package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Microcontrolador;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class MicrocontroladorController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		models.main.Area area = new models.main.Area( );
		area.setId( idArea );
		microcontrolador.setArea( area );
		microcontrolador.save( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( Long idArea )
	{
		List<Microcontrolador> microcontrolador = Microcontrolador.find.where( ).eq( "area.id", idArea ).findList( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieve( Long id )
	{
		Microcontrolador microcontrolador = Microcontrolador.find.byId( id );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		microcontrolador.setId( id );
		microcontrolador.update( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Microcontrolador microcontrolador = Microcontrolador.find.byId( id );
		microcontrolador.delete( );
		return ok( play.libs.Json.toJson( microcontrolador ) );
	}

	public Result getAll( )
	{
		List<Microcontrolador> microcontrolador = Microcontrolador.find.all( );
		return ok( Json.toJson( microcontrolador ) );
	}
}
