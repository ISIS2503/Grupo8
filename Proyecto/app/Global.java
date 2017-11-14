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
		List<Usuario> usuarios = Usuario.find.all( );
		if( usuarios.size( ) == 0 )
		{
			Rol adminRol = new Rol( );
			adminRol.setId( 1L );

			Usuario nuevo = new Usuario( );
			nuevo.setLogin( "admin" );
			nuevo.setPassword( "admin" );
			nuevo.setRoles( Collections.singletonList( adminRol ) );
			nuevo.save( );
		}
	}
}
