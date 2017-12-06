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
public class Area extends Model
{
	public static final Model.Finder<Long, Area> find = new Finder<>( Area.class );

	@Id
	private Long id;

	private Integer tipo;

	@javax.persistence.OneToMany( mappedBy = "area" )
	private List<Actuador> actuadores;

	@javax.persistence.OneToMany( mappedBy = "area" )
	private List<Alerta> alertas;

	@javax.persistence.OneToMany( mappedBy = "area" )
	private List<Microcontrolador> microcontroladores;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.persistence.ManyToOne
	private Nivel nivel;

	public Area( )
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

	public Integer getTipo( )
	{
		return tipo;
	}

	public void setTipo( Integer tipo )
	{
		this.tipo = tipo;
	}

	public List<Actuador> getActuadores( )
	{
		return actuadores;
	}

	public void setActuadores( List<Actuador> actuadores )
	{
		this.actuadores = actuadores;
	}

	public List<Alerta> getAlertas( )
	{
		return alertas;
	}

	public void setAlertas( List<Alerta> alertas )
	{
		this.alertas = alertas;
	}

	public List<Microcontrolador> getMicrocontroladores( )
	{
		return microcontroladores;
	}

	public void setMicrocontroladores( List<Microcontrolador> microcontroladores )
	{
		this.microcontroladores = microcontroladores;
	}

	public Nivel getNivel( )
	{
		return nivel;
	}

	public void setNivel( Nivel nivel )
	{
		this.nivel = nivel;
	}

	public static Area bind( JsonNode json )
	{
		Area area = new Area( );
		area.setId( json.findPath( "id" ).asLong( ) );
		area.setTipo( json.findPath( "tipo" ).asInt( ) );
		//TODO Lists
		return area;
	}
}
