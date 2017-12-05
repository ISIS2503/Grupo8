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
		alerta.setId( json.findPath( "id" ).asLong( ) );
		alerta.setTipo( json.findPath( "tipo" ).asInt( ) );
		return alerta;
	}
}
