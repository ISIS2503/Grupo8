package models;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.ebean.Model;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class Dato extends Model
{
	private Long id;

	private Float valor;

	private ZonedDateTime timeStamp;

	private Long idSensor;

	public Dato( )
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

	public Float getValor( )
	{
		return valor;
	}

	public void setValor( Float valor )
	{
		this.valor = valor;
	}

	public ZonedDateTime getTimeStamp( )
	{
		return timeStamp;
	}

	public void setTimeStamp( ZonedDateTime timeStamp )
	{
		this.timeStamp = timeStamp;
	}

	public Long getIdSensor( )
	{
		return idSensor;
	}

	public void setIdSensor( Long idSensor )
	{
		this.idSensor = idSensor;
	}

	public static Dato bind( JsonNode json )
	{
		Dato dato = new Dato( );
		dato.id = json.findPath( "id" ).asLong( );
		dato.valor = json.findPath( "valor" ).floatValue( );
		Instant i = Instant.ofEpochSecond( json.findPath( "inicio" ).asLong( ) );
		dato.timeStamp = ZonedDateTime.ofInstant( i, ZoneId.systemDefault( ) );
		return dato;
	}
}
