package models.com.arquisix.sistemaminas.logic;

import models.com.arquisix.sistemaminas.entities.SistemaMinas;
import models.com.arquisix.sistemaminas.persistence.SistemaMinasPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class SistemaMinasLogic
{
    @Inject
    private SistemaMinasPersistence persistence;

    public SistemaMinas create(SistemaMinas entity)
    {
        return persistence.create(entity);
    }

    public List<SistemaMinas> retrieveAll()
    {
        return persistence.retrieveAll();
    }

    public SistemaMinas retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public SistemaMinas update(Long id, SistemaMinas entity)
    {
        return persistence.update(id, entity);
    }

    public SistemaMinas delete(Long id)
    {
        return persistence.delete(id);
    }
}
