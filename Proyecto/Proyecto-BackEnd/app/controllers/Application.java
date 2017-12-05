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
		String username = jsonNode.findPath( "username" ).asText( );
		String password = jsonNode.findPath( "password" ).asText( );

		Usuario usuario = Usuario.find.where( ).eq( "login", username ).eq( "password", password ).findUnique( );
		if( usuario != null )
		{
			System.out.println( String.format( "Login: %s", username ) );
			usuario.setPassword( null );
			// session( "user", username );

			usuario.setToken( new RandomString( ).nextString( ) );
			usuario.update( "useresdb" );
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
			usuario.update( "useresdb" );
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
		Usuario usuarioActual = getUsuario( );
		if( usuarioActual != null )
		{
			usuarioActual.setPassword( null );
			return ok( Json.toJson( usuarioActual ) );
		}
		return badRequest( "No hay un usuario conectado" );
	}

	private Usuario getUsuario( )
	{
		String login = request( ).headers( ).get( "user" )[ 0 ];
		String[] tokens = request( ).headers( ).get( "token" );
		if( tokens.length == 0 )
		{
			return null;
		}
		String token = tokens[ 0 ];
		return Usuario.find.where( ).eq( "login", login ).eq( "token", token ).findUnique( );
	}
}
