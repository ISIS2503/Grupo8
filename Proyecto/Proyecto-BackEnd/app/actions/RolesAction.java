package actions;

import models.Rol;
import models.Usuario;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class RolesAction extends play.mvc.Action<RolesAllowed>
{
	@Override
	public F.Promise<Result> call( Http.Context ctx ) throws Throwable
	{
		String login = ctx.session( ).get( "user" );
		Usuario usuario = Usuario.find.where( ).eq( "login", login ).findUnique( );

		if( usuario != null )
		{
			if( Collections.disjoint( usuario.getRoles( ).stream( ).map( Rol::getName ).collect( Collectors.toList( ) ),
									  Arrays.stream( configuration.value( ) ).map( Enum::name ).collect( Collectors.toList( ) ) ) )
			{
				return F.Promise.promise( ( ) -> forbidden( "El usuario no tiene acceso a esta sección" ) );
			}

			return this.delegate.call( ctx );
		}
		return F.Promise.promise( ( ) -> forbidden( "Debe estar logeado para ver esta sección" ) );
	}
}
