package models.users;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dnarvaez27
 */
@Entity
public class Usuario extends Model
{
	public static final Model.Finder<Long, Usuario> find = new Finder<>( "usersdb", Usuario.class );

	@Id
	@GeneratedValue
	private Long id;

	@ManyToMany( cascade = CascadeType.REMOVE )
	private List<Rol> roles;

	@Column( unique = true )
	private String login;

	private String password;

	private String token;

	public Usuario( )
	{
		this.roles = new LinkedList<>( );
	}

	public Long getId( )
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
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

	public List<Rol> getRoles( )
	{
		return roles;
	}

	public void setRoles( List<Rol> roles )
	{
		this.roles = roles;
	}

	public String getToken( )
	{
		return token;
	}

	public void setToken( String token )
	{
		this.token = token;
	}

	public static Usuario bind( JsonNode json )
	{
		Usuario usuario = new Usuario( );
		usuario.setId( json.findPath( "id" ).asLong( ) );
		usuario.setLogin( json.findPath( "login" ).asText( ) );
		usuario.setPassword( json.findPath( "password" ).asText( ) );
		usuario.setRoles( new LinkedList<>( ) );

		for( JsonNode j : json.findPath( "roles" ) )
		{
			Long id = j.findPath( "id" ).asLong( );
			Rol rol = new Rol( );
			rol.setId( id );
			usuario.roles.add( rol );
		}

		return usuario;
	}
}
