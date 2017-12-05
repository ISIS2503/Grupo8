package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;

/**
 * @author dnarvaez27
 */
@Entity
public class Reporte extends Model
{
	public static final Model.Finder<Long, Reporte> find = new Finder<>( Reporte.class );

	@Id
	private Long id;

	private Date fecha;

	@javax.persistence.ManyToOne
	private Nivel nivel;

	public Reporte( )
	{
	}

	public Reporte( Date fecha )
	{
		this.fecha = fecha;
	}

	public Date getFecha( )
	{
		return fecha;
	}

	public void setFecha( Date fecha )
	{
		this.fecha = fecha;
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public Nivel getNivel( )
	{
		return nivel;
	}

	public void setNivel( Nivel nivel )
	{
		this.nivel = nivel;
	}

	public static Reporte bind( JsonNode json )
	{
		Reporte reporte = new Reporte( );
		reporte.setId( json.findPath( "id" ).asLong( ) );
		reporte.setFecha( new Date( json.findPath( "inicio" ).asLong( ) ) );
		return reporte;
	}
}
