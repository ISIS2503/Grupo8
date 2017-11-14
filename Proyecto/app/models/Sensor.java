package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author dnarvaez27
 */
@Entity
public class Sensor extends Model
{
	public static final Model.Finder<Long, Sensor> find = new Finder<>( Sensor.class );

	@Id
	private Long id;

	private Float minimo;

	private Float maximo;

	private VariableAmbiental tipo;

	private List<Dato> datos;

	private Long idMicrocontrolador;

	public Sensor( )
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

	public Float getMinimo( )
	{
		return minimo;
	}

	public void setMinimo( Float minimo )
	{
		this.minimo = minimo;
	}

	public Float getMaximo( )
	{
		return maximo;
	}

	public void setMaximo( Float maximo )
	{
		this.maximo = maximo;
	}

	public VariableAmbiental getTipo( )
	{
		return tipo;
	}

	public void setTipo( VariableAmbiental tipo )
	{
		this.tipo = tipo;
	}

	public List<Dato> getDatos( )
	{
		return datos;
	}

	public void setDatos( List<Dato> datos )
	{
		this.datos = datos;
	}

	public Long getIdMicrocontrolador( )
	{
		return idMicrocontrolador;
	}

	public void setIdMicrocontrolador( Long idMicrocontrolador )
	{
		this.idMicrocontrolador = idMicrocontrolador;
	}

	public static Sensor bind( JsonNode json )
	{
		Sensor sensor = new Sensor( );
		sensor.id = json.findPath( "id" ).asLong( );
		sensor.maximo = json.findPath( "maximo" ).floatValue( );
		sensor.minimo = json.findPath( "minimo" ).floatValue( );
		sensor.tipo = VariableAmbiental.bind( json.findPath( "tipo" ) );
		return sensor;
	}
}

