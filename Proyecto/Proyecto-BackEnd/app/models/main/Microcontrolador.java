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

	@javax.persistence.OneToMany( mappedBy = "microcontrolador" )
	private List<Sensor> sensores;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.persistence.ManyToOne
	@javax.persistence.Column( nullable = false )
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

	public void setArea( Area area )
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

