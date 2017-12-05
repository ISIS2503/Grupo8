package actions;

import models.users.Rol;
import models.users.Usuario;
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
		// String login = ctx.session( ).get( "user" );
		String[] loginHead = ctx.request( ).headers( ).get( "user" );
		String[] tokens = ctx.request( ).headers( ).get( "token" );
		if( tokens == null || tokens.length == 0 || loginHead == null || loginHead.length == 0 )
		{
			return notLoggedIn( );
		}
		String login = loginHead[ 0 ];
		String token = tokens[ 0 ];

		Usuario usuario = Usuario.find.where( ).eq( "login", login ).eq( "token", token ).findUnique( );

		if( usuario != null )
		{
			if( Collections.disjoint( usuario.getRoles( ).stream( ).map( Rol::getName ).collect( Collectors.toList( ) ),
									  Arrays.stream( configuration.value( ) ).map( Enum::name ).collect( Collectors.toList( ) ) ) )
			{
				return notAllowed( );
			}

			return this.delegate.call( ctx );
		}
		return notLoggedIn( );
	}

	private F.Promise<Result> notAllowed( )
	{
		System.out.println( "El usuario no tiene acceso a esta sección" );
		return F.Promise.promise( ( ) -> forbidden( "El usuario no tiene acceso a esta sección" ) );
	}

	private F.Promise<Result> notLoggedIn( )
	{
		System.out.println( "Debe estar logeado para ver esta sección" );
		return F.Promise.promise( ( ) -> forbidden( "Debe estar logeado para ver esta sección" ) );
	}
}
