package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author dnarvaez27
 */
@Entity
public class Dato extends Model
{
	public static final Model.Finder<Long, Dato> find = new Finder<>( Dato.class );

	@Id
	@GeneratedValue
	private Long id;

	private Float valor;

	private Date timeStamp;

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

	public Date getTimeStamp( )
	{
		return timeStamp;
	}

	public void setTimeStamp( Date timeStamp )
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
		dato.setId( json.findPath( "id" ).asLong( ) );
		dato.setValor( json.findPath( "valor" ).floatValue( ) );
		dato.setTimeStamp( new Date( json.findPath( "inicio" ).asLong( ) ) );
		return dato;
	}
}
