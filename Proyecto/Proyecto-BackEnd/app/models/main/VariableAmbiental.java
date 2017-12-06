package models.main;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author dnarvaez27
 */
@Entity
public class VariableAmbiental extends Model
{
	public static final Model.Finder<Long, VariableAmbiental> find = new Finder<>( VariableAmbiental.class );

	@Id
	private Long id;

	private Float valorMaximo;

	private Float valorMinimo;

	private Float variacion;

	private String uniadadDeMedida;

	private Float precision;

	private Float frecuencia;

	private String nombre;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@javax.persistence.OneToMany( mappedBy = "tipo" )
	private java.util.List<Sensor> sensores;

	public VariableAmbiental( )
	{
	}

	public Float getValorMaximo( )
	{

		return valorMaximo;
	}

	public void setValorMaximo( Float valorMaximo )
	{
		this.valorMaximo = valorMaximo;
	}

	public Float getValorMinimo( )
	{
		return valorMinimo;
	}

	public void setValorMinimo( Float valorMinimo )
	{
		this.valorMinimo = valorMinimo;
	}

	public Float getVariacion( )
	{
		return variacion;
	}

	public void setVariacion( Float variacion )
	{
		this.variacion = variacion;
	}

	public String getUniadadDeMedida( )
	{
		return uniadadDeMedida;
	}

	public void setUniadadDeMedida( String uniadadDeMedida )
	{
		this.uniadadDeMedida = uniadadDeMedida;
	}

	public Float getPrecision( )
	{
		return precision;
	}

	public void setPrecision( Float precision )
	{
		this.precision = precision;
	}

	public Float getFrecuencia( )
	{
		return frecuencia;
	}

	public void setFrecuencia( Float frecuencia )
	{
		this.frecuencia = frecuencia;
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getNombre( )
	{
		return nombre;
	}

	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}

	public java.util.List<Sensor> getSensores( )
	{
		return sensores;
	}

	public void setSensores( java.util.List<Sensor> sensores )
	{
		this.sensores = sensores;
	}

	public static VariableAmbiental bind( JsonNode json )
	{
		VariableAmbiental var = new VariableAmbiental( );
		var.setId( json.findPath( "id" ).asLong( ) );
		var.setValorMaximo( json.findPath( "valorMaximo" ).floatValue( ) );
		var.setValorMinimo( json.findPath( "valorMinimo" ).floatValue( ) );
		var.setVariacion( json.findPath( "variacion" ).floatValue( ) );
		var.setUniadadDeMedida( json.findPath( "uniadadDeMedida" ).asText( ) );
		var.setPrecision( json.findPath( "precision" ).floatValue( ) );
		var.setFrecuencia( json.findPath( "frecuencia" ).floatValue( ) );
		var.setNombre( json.findPath( "nombre" ).asText( ) );
		return var;
	}
}
