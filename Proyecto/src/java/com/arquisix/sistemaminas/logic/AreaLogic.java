package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Area;
import com.arquisix.sistemaminas.persistence.AreaPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class AreaLogic
{
    @Inject
    private AreaPersistence persistence;

    public Area create(Long idNivel, Area entity)
    {
        return persistence.create(idNivel, entity);
    }

    public List<Area> retrieveAll(Long idNivel)
    {
        return persistence.retrieveAll(idNivel);
    }

    public Area retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Area update(Long idNivel, Long id, Area entity)
    {
        return persistence.update(id, entity);
    }

    public Area delete(Long idNivel, Long idArea )
    {
        return persistence.delete(idArea);
    }

}
