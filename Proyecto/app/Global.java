import com.avaje.ebean.Model;
import models.Usuario;
import play.Application;
import play.GlobalSettings;

import java.util.List;

public class Global extends GlobalSettings
{
    @Override
    public void onStart(Application app)
    {
        List<Usuario> usuarios = new Model.Finder<Long, Usuario>(Usuario.class).all();
        if (usuarios.size() == 0)
        {
            Usuario nuevo = new Usuario();
            nuevo.setLogin("d.naravez11");
            nuevo.save();
        }
    }
}
