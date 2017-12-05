package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Rol;
import models.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

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
		System.out.println( "Login "+ username );
		if( usuario != null )
		{
			usuario.setPassword( null );
			session( "user", username );
			return ok( Json.toJson( usuario ) );
		}
		return badRequest( "Verificar credenciales" );
	}

	public Result logout( )
	{
		session( ).remove( "user" );
		return ok( "Bye" );
	}

	public Result rolesActualUser( )
	{
		String user = session( ).get( "user" );
		Usuario usuarioActual = Usuario.find.where( ).eq( "login", user ).findUnique( );

		ObjectNode json = JsonNodeFactory.instance.objectNode( );
		for( Rol rol : usuarioActual.getRoles( ) )
		{
			json.put( rol.getName( ), true );
		}
		return ok( json );
	}

	public Result actualUser( )
	{
		String user = session( ).get( "user" );
		Usuario usuarioActual = Usuario.find.where( ).eq( "login", user ).findUnique( );
		if( usuarioActual != null )
		{
			usuarioActual.setPassword( null );
			return ok( Json.toJson( usuarioActual ) );
		}
		return badRequest( "No hay un usuario conectado" );
	}
}
