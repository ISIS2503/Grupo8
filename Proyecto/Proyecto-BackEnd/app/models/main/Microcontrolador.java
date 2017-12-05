package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author dnarvaez27
 */
@Entity
public class Microcontrolador extends Model
{
	public static final Model.Finder<Long, Microcontrolador> find = new Finder<>( Microcontrolador.class );

	@Id
	private Long id;

	private List<Sensor> sensores;

	@javax.persistence.ManyToOne
	private Area area;

	public Microcontrolador( )
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

	public List<Sensor> getSensores( )
	{
		return sensores;
	}

	public void setSensores( List<Sensor> sensores )
	{
		this.sensores = sensores;
	}

	public Area getArea( )
	{
		return area;
	}

	public void setArea( Area idArea )
	{
		this.area = area;
	}

	public static Microcontrolador bind( JsonNode json )
	{
		Microcontrolador microcontrolador = new Microcontrolador( );
		microcontrolador.setId( json.findPath( "id" ).asLong( ) );
		return microcontrolador;
	}
}

