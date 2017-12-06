package models.main;

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

	@javax.persistence.ManyToOne
	private Sensor sensor;

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

	public Sensor getSensor( )
	{
		return sensor;
	}

	public void setSensor( Sensor sensor )
	{
		this.sensor = sensor;
	}

	public static Dato bind( JsonNode json )
	{
		java.text.DateFormat format = new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm" );
		Dato dato = new Dato( );
		dato.setId( json.findPath( "id" ).asLong( ) );
		dato.setValor( json.findPath( "valor" ).floatValue( ) );
		String timeStamp = json.findPath( "timeStamp" ).asText( );
		timeStamp = timeStamp.replace( "T", " " );

		try
		{
			dato.setTimeStamp( format.parse( timeStamp ) );
		}
		catch( java.text.ParseException e )
		{
			e.printStackTrace( );
		}
		return dato;
	}
}
