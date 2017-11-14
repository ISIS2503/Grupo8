package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.Actuador;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class ActuadorController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Actuador actuador = Actuador.bind( json );
		actuador.setIdArea( idArea );
		actuador.save( );
		return ok( Json.toJson( actuador ) );
	}

	@RolesAllowed( { Roles.SYSO, Roles.ADMIN } )
	public Result retrieveAll( Long idArea )
	{

		List<Actuador> actuador = Actuador.find.where( ).eq( "idArea", idArea ).findList( );
		return ok( Json.toJson( actuador ) );
	}

	@RolesAllowed( { Roles.SYSO, Roles.ADMIN } )
	public Result retrieve( Long id )
	{
		Actuador actuador = Actuador.find.byId( id );
		return ok( Json.toJson( actuador ) );
	}

	@RolesAllowed( { Roles.SYSO, Roles.ADMIN } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Actuador actuador = Actuador.bind( json );
		actuador.setId( id );
		actuador.save( );
		return ok( Json.toJson( actuador ) );
	}

	@RolesAllowed( { Roles.SYSO, Roles.ADMIN } )
	public Result delete( Long id )
	{
		Actuador actuador = Actuador.find.byId( id );
		actuador.delete( );
		return ok( Json.toJson( actuador ) );
	}
}
