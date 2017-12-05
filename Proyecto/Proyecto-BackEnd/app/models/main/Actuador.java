package models.main;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author dnarvaez27
 */
@Entity
public class Actuador extends Model
{
	public static final Model.Finder<Long, Actuador> find = new Finder<>( Actuador.class );

	@Id
	private Long id;

	private Boolean activo;

	private Date inicio;

	private Long idArea;

	public Actuador( )
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

	public Boolean getActivo( )
	{
		return activo;
	}

	public void setActivo( Boolean activo )
	{
		this.activo = activo;
	}

	public Date getInicio( )
	{
		return inicio;
	}

	public void setInicio( Date inicio )
	{
		this.inicio = inicio;
	}

	public Long getIdArea( )
	{
		return idArea;
	}

	public void setIdArea( Long idArea )
	{
		this.idArea = idArea;
	}

	public static Actuador bind( com.fasterxml.jackson.databind.JsonNode json )
	{
		Actuador actuador = new Actuador( );
		actuador.setId( json.findPath( "id" ).asLong( ) );
		actuador.setActivo( json.findPath( "activo" ).asBoolean( ) );
		actuador.setInicio( new Date( json.findPath( "inicio" ).asLong( ) ) );
		return actuador;
	}
}
