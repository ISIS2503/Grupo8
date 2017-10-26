package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author dnarvaez27
 */
@Entity
public class Reporte extends Model
{
	private Long id;

	private Date fecha;

	private long idNivel;

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

	public long getIdNivel( )
	{
		return idNivel;
	}

	public void setIdNivel( long idNivel )
	{
		this.idNivel = idNivel;
	}

	public static Reporte bind( JsonNode json )
	{
		Reporte reporte = new Reporte( );
		reporte.id = json.findPath( "id" ).asLong( );
		reporte.fecha = new Date( json.findPath( "inicio" ).asLong( ) );
		return reporte;
	}
}
