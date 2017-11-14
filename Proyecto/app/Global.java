import models.Rol;
import models.Usuario;
import play.Application;
import play.GlobalSettings;

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
			adminRol.save( );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );
			sysoRol.setName( "SYSO" );
			sysoRol.save( );

			Rol userRol = new Rol( );
			userRol.setId( 3L );
			userRol.setName( "USER" );
			userRol.save( );
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
			admin.save( );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );

			Usuario syso = new Usuario( );
			syso.setLogin( "syso" );
			syso.setPassword( "syso" );
			syso.setRoles( Collections.singletonList( sysoRol ) );
			syso.save( );
		}
	}
}
