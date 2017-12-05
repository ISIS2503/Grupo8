package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.users.Rol;
import models.users.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import utils.RandomString;

public class Application extends Controller
{
	public Result index( )
	{
		// javax.swing.JOptionPane.showMessageDialog( new javax.swing.JFrame( ), "" +  );
		// return ok( index.render( "Your new application is ready." ) );
		return ok( "Your new application is ready." );
	}

	@BodyParser.Of( BodyParser.Json.class )
	public Result login( )
	{
		JsonNode jsonNode = request( ).body( ).asJson( );
		String login = jsonNode.findPath( "login" ).asText( );
		String password = jsonNode.findPath( "password" ).asText( );

		Usuario usuario = Usuario.find.where( ).eq( "login", login ).eq( "password", password ).findUnique( );
		if( usuario != null )
		{
			System.out.println( String.format( "Login: %s", login ) );
			String token = new RandomString( ).nextString( );

			usuario.setToken( token );
			usuario.update( "usersdb" );

			session( "user", login );
			session( "token", token );

			usuario.setPassword( null );
			return ok( Json.toJson( usuario ) );
		}
		return badRequest( "Verificar credenciales" );
	}

	public Result logout( )
	{
		Usuario usuario = getUsuario( );
		if( usuario != null )
		{
			usuario.setToken( null );
			usuario.update( "usersdb" );
			session( ).remove( "user" );
			session( ).remove( "token" );
			session( ).clear( );
			return ok( "Bye" );
		}
		return ok( "Must be logged in" );
	}

	public Result rolesActualUser( )
	{
		ObjectNode json = JsonNodeFactory.instance.objectNode( );
		Usuario usuarioActual = getUsuario( );
		if( usuarioActual != null )
		{
			for( Rol rol : usuarioActual.getRoles( ) )
			{
				json.put( rol.getName( ), true );
			}
		}
		return ok( json );
	}

	public Result actualUser( )
	{
		String user = session( ).get( "user" );
		String token = session( ).get( "token" );
		Usuario usuarioActual = Usuario.find.where( ).eq( "login", user ).eq( "token", token ).findUnique( );
		if( usuarioActual != null )
		{
			usuarioActual.setPassword( null );
			return ok( Json.toJson( usuarioActual ) );
		}
		return badRequest( "No hay un usuario conectado" );
	}

	private Usuario getUsuario( )
	{
		String[] userHeader = request( ).headers( ).get( "user" );
		String[] tokens = request( ).headers( ).get( "token" );
		if( tokens == null || userHeader == null || tokens.length == 0 || userHeader.length == 0 )
		{
			return null;
		}
		String login = userHeader[ 0 ];
		String token = tokens[ 0 ];
		return Usuario.find.where( ).eq( "login", login ).eq( "token", token ).findUnique( );
	}
}
