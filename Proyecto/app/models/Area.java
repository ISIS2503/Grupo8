package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Area extends Model
{
	private Long id;

	private Integer tipo;

	private List<Actuador> actuadores;

	private List<Alerta> alertas;

	private List<Microcontrolador> microcontroladores;

	private Long idNivel;

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

	public Long getIdNivel( )
	{
		return idNivel;
	}

	public void setIdNivel( Long idNivel )
	{
		this.idNivel = idNivel;
	}

	public static Area bind( JsonNode json )
	{
		Area area = new Area( );
		area.id = json.findPath( "id" ).asLong( );
		area.tipo = json.findPath( "tipo" ).asInt( );
		//TODO Lists
		return area;
	}
}
