package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author dnarvaez27
 */
@Entity
public class Actuador extends Model
{
	private Long id;

	private Boolean activo;

	private ZonedDateTime inicio;

	private Long idArea;

	public Actuador( )
	{
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Boolean getActivo( )
	{
		return activo;
	}

	public void setActivo( Boolean activo )
	{
		this.activo = activo;
	}

	public ZonedDateTime getInicio( )
	{
		return inicio;
	}

	public void setInicio( ZonedDateTime inicio )
	{
		this.inicio = inicio;
	}

	public Long getIdArea( )
	{
		return idArea;
	}

	public void setIdArea( Long idArea )
	{
		this.idArea = idArea;
	}

	public static Actuador bind( com.fasterxml.jackson.databind.JsonNode json )
	{
		Actuador actuador = new Actuador( );
		actuador.id = json.findPath( "id" ).asLong( );
		actuador.activo = json.findPath( "activo" ).asBoolean( );
		Instant i = Instant.ofEpochSecond( json.findPath( "inicio" ).asLong( ) );
		actuador.inicio = ZonedDateTime.ofInstant( i, ZoneId.systemDefault( ) );
		return actuador;
	}
}
