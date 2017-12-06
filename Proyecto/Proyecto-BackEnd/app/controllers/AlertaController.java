package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Alerta;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class AlertaController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idArea )
	{
		JsonNode json = request( ).body( ).asJson( );
		Alerta alerta = Alerta.bind( json );
		models.main.Area area = new models.main.Area( );
		area.setId( idArea );
		alerta.setArea( area );
		alerta.save( );
		return ok( Json.toJson( alerta ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( Long idArea )
	{
		List<Alerta> alerta = Alerta.find.where( ).eq( "idArea", idArea ).findList( );
		return ok( Json.toJson( alerta ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieve( Long id )
	{
		Alerta alerta = Alerta.find.byId( id );
		return ok( Json.toJson( alerta ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Alerta alerta = Alerta.bind( json );
		alerta.setId( id );
		alerta.update( );
		return ok( Json.toJson( alerta ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Alerta alerta = Alerta.find.byId( id );
		alerta.delete( );
		return ok( Json.toJson( alerta ) );
	}
}
