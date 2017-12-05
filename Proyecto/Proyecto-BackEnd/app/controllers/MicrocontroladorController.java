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
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		microcontrolador.setIdArea( idArea );
		microcontrolador.save( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( Long idArea )
	{
		List<Microcontrolador> microcontrolador = Microcontrolador.find.where( ).eq( "idArea", idArea ).findList( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		Microcontrolador microcontrolador = Microcontrolador.find.byId( id );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Microcontrolador microcontrolador = Microcontrolador.bind( json );
		microcontrolador.setId( id );
		microcontrolador.update( );
		return ok( Json.toJson( microcontrolador ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		Microcontrolador microcontrolador = Microcontrolador.find.byId( id );
		microcontrolador.delete( );
		return ok( play.libs.Json.toJson( microcontrolador ) );
	}
}
