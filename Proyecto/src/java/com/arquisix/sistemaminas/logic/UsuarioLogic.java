package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Usuario;
import com.arquisix.sistemaminas.persistence.UsuarioPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class UsuarioLogic
{
    @Inject
    private UsuarioPersistence persistence;

    public Usuario create(Usuario entity)
    {
        return persistence.create(entity);
    }

    public List<Usuario> retrieveAll()
    {
        return persistence.retrieveAll();
    }

    public Usuario retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Usuario update(Long id, Usuario entity)
    {
        return persistence.update(id, entity);
    }

    public Usuario delete(Long id)
    {
        return persistence.delete(id);
    }
}
