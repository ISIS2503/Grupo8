package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Reporte extends Model
{
	private Long id;

	private ZonedDateTime fecha;

	private List<Nivel> niveles;

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

	public java.util.List<Nivel> getNiveles( )
	{
		return niveles;
	}

	public void setNiveles( java.util.List<Nivel> niveles )
	{
		this.niveles = niveles;
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
