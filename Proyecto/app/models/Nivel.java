package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Nivel extends Model
{
	private Long id;

	private Integer nivel;

	private List<Area> areas;

	private List<Reporte> reportes;

	public Nivel( )
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

	public Integer getNivel( )
	{
		return nivel;
	}

	public void setNivel( Integer nivel )
	{
		this.nivel = nivel;
	}

	public List<Area> getAreas( )
	{
		return areas;
	}

	public void setAreas( List<Area> areas )
	{
		this.areas = areas;
	}

	public java.util.List<Reporte> getReportes( )
	{
		return reportes;
	}

	public void setReportes( java.util.List<Reporte> reportes )
	{
		this.reportes = reportes;
	}

	public static Nivel bind( JsonNode json )
	{
		Nivel nivel = new Nivel( );
		nivel.id = json.findPath( "id" ).asLong( );
		nivel.nivel = json.findPath( "nivel" ).asInt( );
		return nivel;
	}
}
