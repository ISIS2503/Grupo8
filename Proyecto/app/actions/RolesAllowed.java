package actions;

import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@With( RolesAction.class )
@Target( { ElementType.METHOD } )
@Retention( RetentionPolicy.RUNTIME )
public @interface RolesAllowed
{
	Roles[] value( );
}
