package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import models.Reporte;
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
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idNivel )
	{
		JsonNode json = request( ).body( ).asJson( );
		Reporte reporte = Reporte.bind( json );
		reporte.setIdNivel( idNivel );
		reporte.save( );
		return ok( Json.toJson( reporte ) );
	}

	public Result retrieveAll( Long idNivel )
	{
		List<Reporte> reporte = new Model.Finder<Long, Reporte>( Reporte.class ).where( ).eq( "idNivel", idNivel ).findList( );
		return ok( Json.toJson( reporte ) );
	}

	public Result retrieveAll( )
	{
		List<Reporte> reporte = new Model.Finder<Long, Reporte>( Reporte.class ).findList( );
		return ok( Json.toJson( reporte ) );
	}

	public Result retrieve( Long id )
	{
		Reporte reporte = new Model.Finder<Long, Reporte>( Reporte.class ).byId( id );
		return ok( Json.toJson( reporte ) );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Reporte reporte = Reporte.bind( json );
		reporte.setId( id );
		reporte.save( );
		return ok( Json.toJson( reporte ) );
	}

	public Result delete( Long id )
	{
		Reporte reporte = new Model.Finder<Long, Reporte>( Reporte.class ).byId( id );
		reporte.delete( );
		return ok( Json.toJson( reporte ) );
	}
}
