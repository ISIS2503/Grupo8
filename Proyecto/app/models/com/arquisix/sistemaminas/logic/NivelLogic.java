package models.com.arquisix.sistemaminas.logic;

import models.com.arquisix.sistemaminas.entities.Nivel;
import models.com.arquisix.sistemaminas.persistence.NivelPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class NivelLogic
{
    @Inject
    private NivelPersistence persistence;

    public Nivel create(Nivel entity)
    {
        return persistence.create(entity);
    }

    public List<Nivel> retrieveAll()
    {
        return persistence.retrieveAll();
    }

    public Nivel retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Nivel update(Long id, Nivel entity)
    {
        return persistence.update(id, entity);
    }

    public Nivel delete(Long id)
    {
        return persistence.delete(id);
    }
}
