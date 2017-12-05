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

	private Long idArea;

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

	public Long getIdArea( )
	{
		return idArea;
	}

	public void setIdArea( Long idArea )
	{
		this.idArea = idArea;
	}

	public static Microcontrolador bind( JsonNode json )
	{
		Microcontrolador microcontrolador = new Microcontrolador( );
		microcontrolador.setId( json.findPath( "id" ).asLong( ) );
		return microcontrolador;
	}
}

