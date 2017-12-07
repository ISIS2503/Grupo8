package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private Long id;

	private Boolean activo;

	private Date inicio;

	@JsonIgnore
	@javax.persistence.ManyToOne
	private Area area;

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

	public Area getArea( )
	{
		return area;
	}

	public void setArea( Area idArea )
	{
		this.area = area;
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
