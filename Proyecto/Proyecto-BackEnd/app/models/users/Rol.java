package models.users;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rol extends Model
{
	public static final Model.Finder<Long, Rol> find = new Finder<>( "usersdb", Rol.class );

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
		rol.setId( json.findPath( "id" ).asLong( ) );
		rol.setName( json.findPath( "name" ).asText( ) );
		return rol;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
