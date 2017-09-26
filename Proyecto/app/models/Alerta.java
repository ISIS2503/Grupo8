package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;

/**
 * Created by juanchaves on 24/09/17.
 */
@Entity
public class Alerta extends Model
{
	private Long id;

	private Integer tipo;

	private Long idArea;

	public Alerta( )
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

	public Long getIdArea( )
	{
		return idArea;
	}

	public void setIdArea( Long idArea )
	{
		this.idArea = idArea;
	}

	public static Alerta bind( JsonNode json )
	{
		Alerta alerta = new Alerta( );
		alerta.id = json.findPath( "id" ).asLong( );
		alerta.tipo = json.findPath( "tipo" ).asInt( );
		return alerta;
	}
}
