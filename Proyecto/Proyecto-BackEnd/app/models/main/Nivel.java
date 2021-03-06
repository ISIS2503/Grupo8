package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dnarvaez27
 */
@Entity
public class Nivel extends Model
{
	public static final Model.Finder<Long, Nivel> find = new Finder<>( Nivel.class );

	@Id
	private Long id;

	@javax.persistence.Column( unique = true )
	private Integer nivel;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "nivel" )
	private List<Area> areas;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "nivel" )
	private List<Reporte> reportes;

	public Nivel( )
	{
		areas = new LinkedList<>( );
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

	public List<Reporte> getReportes( )
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
		nivel.setId( json.findPath( "id" ).asLong( ) );
		nivel.setNivel( json.findPath( "nivel" ).asInt( ) );
		nivel.setAreas( new LinkedList<>( ) );
		nivel.setReportes( new LinkedList<>( ) );
		return nivel;
	}
}
