package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;

@Entity
public class Usuario extends Model
{
	private Long id;

	private String rol;

	private String login;

	private String password;

	public Usuario( )
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

	public String getRol( )
	{
		return rol;
	}

	public void setRol( String rol )
	{
		this.rol = rol;
	}

	public String getLogin( )
	{
		return login;
	}

	public void setLogin( String login )
	{
		this.login = login;
	}

	public String getPassword( )
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public static Usuario bind( JsonNode json )
	{
		Usuario usuario = new Usuario( );
		usuario.id = json.findPath( "is" ).asLong( );
		usuario.login = json.findPath( "login" ).asText( );
		usuario.rol = json.findPath( "rol" ).asText( );
		usuario.password = json.findPath( "password" ).asText( ); //TODO
		return usuario;
	}
}
