package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dnarvaez27
 */
public class SistemaMinas extends Model
{
	public static final Model.Finder<Long, SistemaMinas> find = new Finder<>( SistemaMinas.class );

	@Id
	private Long id;

	private Map<Long, Long[]> mapaSensores;

	private List<Nivel> niveles;

	private List<Reporte> reportes;

	private List<Usuario> usuarios;

	public SistemaMinas( )
	{
		mapaSensores = new HashMap<>( );
	}

	public Map<Long, Long[]> getMapaSensores( )
	{
		return mapaSensores;
	}

	public void setMapaSensores( Map<Long, Long[]> mapaSensores )
	{
		this.mapaSensores = mapaSensores;
	}

	public List<Nivel> getNiveles( )
	{
		return niveles;
	}

	public void setNiveles( List<Nivel> niveles )
	{
		this.niveles = niveles;
	}

	public List<Reporte> getReportes( )
	{
		return reportes;
	}

	public void setReportes( List<Reporte> reportes )
	{
		this.reportes = reportes;
	}

	public List<Usuario> getUsuarios( )
	{
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios )
	{
		this.usuarios = usuarios;
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public static SistemaMinas bind( JsonNode json )
	{
		SistemaMinas sistemaMinas = new SistemaMinas( );
		sistemaMinas.id = json.findPath( "id" ).asLong( );
		//TODO
		return sistemaMinas;
	}
}
