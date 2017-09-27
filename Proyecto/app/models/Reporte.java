package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author dnarvaez27
 */
@Entity
public class Reporte extends Model
{
	private Long id;

	private ZonedDateTime fecha;

	private long idNivel;

	public Reporte( )
	{
	}

	public Reporte( ZonedDateTime fecha )
	{
		this.fecha = fecha;
	}

	public ZonedDateTime getFecha( )
	{
		return fecha;
	}

	public void setFecha( ZonedDateTime fecha )
	{
		this.fecha = fecha;
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public long getIdNivel( )
	{
		return idNivel;
	}

	public void setIdNivel( long idNivel )
	{
		this.idNivel = idNivel;
	}

	public static Reporte bind( JsonNode json )
	{
		Reporte reporte = new Reporte( );
		reporte.id = json.findPath( "id" ).asLong( );
		Instant i = Instant.ofEpochSecond( json.findPath( "inicio" ).asLong( ) );
		reporte.fecha = ZonedDateTime.ofInstant( i, ZoneId.systemDefault( ) );
		return reporte;
	}
}
