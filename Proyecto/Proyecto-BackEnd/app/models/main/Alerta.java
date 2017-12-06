package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author dnarvaez27
 */
@Entity
public class Alerta extends Model
{
	public static final Model.Finder<Long, Alerta> find = new Finder<>( Alerta.class );

	@Id
	private Long id;

	private Integer tipo;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.persistence.ManyToOne
	private Area area;

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

	public Area getArea( )
	{
		return area;
	}

	public void setArea( Area idArea )
	{
		this.area = idArea;
	}

	public static Alerta bind( JsonNode json )
	{
		Alerta alerta = new Alerta( );
		alerta.setId( json.findPath( "id" ).asLong( ) );
		alerta.setTipo( json.findPath( "tipo" ).asInt( ) );
		return alerta;
	}
}
