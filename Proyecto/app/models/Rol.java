package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol extends Model
{
	public static final Model.Finder<Long, Rol> find = new Finder<>( Rol.class );

	@Id
	private Long id;

	private String name;

	public Rol( )
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

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public static Rol bind( JsonNode json )
	{
		Rol rol = new Rol( );
		rol.id = json.findPath( "id" ).asLong( );
		rol.name = json.findPath( "name" ).asText( );
		return rol;
	}
}
