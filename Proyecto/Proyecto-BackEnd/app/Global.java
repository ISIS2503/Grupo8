import models.users.Rol;
import models.users.Usuario;
import play.Application;
import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Collections;
import java.util.List;

public class Global extends GlobalSettings
{
	@Override
	public void onStart( Application app )
	{
		List<Rol> rols = Rol.find.all( );
		if( rols.size( ) == 0 )
		{
			Rol adminRol = new Rol( );
			adminRol.setId( 1L );
			adminRol.setName( "ADMIN" );
			adminRol.insert( "usersdb" );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );
			sysoRol.setName( "SYSO" );
			sysoRol.insert( "usersdb" );

			Rol userRol = new Rol( );
			userRol.setId( 3L );
			userRol.setName( "USER" );
			userRol.insert( "usersdb" );

			Rol bridgeRol = new Rol( );
			bridgeRol.setId( 4L );
			bridgeRol.setName( "BRIDGE" );
			bridgeRol.insert( "usersdb" );
		}

		List<Usuario> usuarios = Usuario.find.all( );
		if( usuarios.size( ) == 0 )
		{
			Rol adminRol = new Rol( );
			adminRol.setId( 1L );

			Usuario admin = new Usuario( );
			admin.setLogin( "admin" );
			admin.setPassword( "admin" );
			admin.setRoles( Collections.singletonList( adminRol ) );
			admin.insert( "usersdb" );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );

			Usuario syso = new Usuario( );
			syso.setLogin( "syso" );
			syso.setPassword( "syso" );
			syso.setRoles( Collections.singletonList( sysoRol ) );
			syso.insert( "usersdb" );

			Rol bridgeRol = new Rol( );
			bridgeRol.setId( 4L );

			Usuario bridge = new Usuario( );
			bridge.setLogin( "bridge" );
			bridge.setPassword( "bridge123" );
			bridge.setRoles( Collections.singletonList( bridgeRol ) );
			bridge.insert( "usersdb" );
		}
	}

	@Override
	public F.Promise<Result> onHandlerNotFound( Http.RequestHeader request )
	{
		return F.Promise.promise( ( ) -> Results.notFound( "No se encontró la ruta " + request.path( ) ) );
	}

	@Override
	public F.Promise<Result> onBadRequest( Http.RequestHeader request, String error )
	{
		return F.Promise.promise( ( ) -> Results.badRequest( error ) );
	}

	@Override
	public F.Promise<Result> onError( Http.RequestHeader request, Throwable t )
	{
		t.printStackTrace( );
		return F.Promise.promise( ( ) ->
								  {
									  String message = t.getMessage( );
									  return Results.internalServerError( message != null ? message : "" );
								  } );
	}
}
