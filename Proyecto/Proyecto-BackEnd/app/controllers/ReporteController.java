package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Nivel;
import models.main.Reporte;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class ReporteController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idNivel )
	{
		JsonNode json = request( ).body( ).asJson( );
		Reporte reporte = Reporte.bind( json );
		Nivel nivel = new Nivel( );
		nivel.setId( idNivel );
		reporte.setNivel( nivel );
		reporte.save( );
		return ok( Json.toJson( reporte ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( Long idNivel )
	{
		List<Reporte> reporte = Reporte.find.where( ).eq( "idNivel", idNivel ).findList( );
		return ok( Json.toJson( reporte ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieveAll( )
	{
		List<Reporte> reporte = Reporte.find.findList( );
		return ok( Json.toJson( reporte ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result retrieve( Long id )
	{
		Reporte reporte = Reporte.find.byId( id );
		return ok( Json.toJson( reporte ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Reporte reporte = Reporte.bind( json );
		reporte.setId( id );
		reporte.update( );
		return ok( Json.toJson( reporte ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SYSO } )
	public Result delete( Long id )
	{
		Reporte reporte = Reporte.find.byId( id );
		reporte.delete( );
		return ok( Json.toJson( reporte ) );
	}
}
