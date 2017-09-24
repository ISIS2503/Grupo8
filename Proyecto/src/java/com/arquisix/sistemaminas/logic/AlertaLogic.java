package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Alerta;
import com.arquisix.sistemaminas.persistence.AlertaPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class AlertaLogic
{
    @Inject
    private AlertaPersistence persistence;

    public Alerta create(Long idArea,Alerta entity)
    {
        return persistence.create(idArea,entity);
    }

    public List<Alerta> retrieveAll(Long idArea)
    {
        return persistence.retrieveAll(idArea);
    }

    public Alerta retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Alerta update(Long idArea, Long id, Alerta entity)
    {
        return persistence.update(id, entity);
    }

    public Alerta delete(Long idArea, Long idAlerta)
    {
        return persistence.delete(idAlerta);
    }
}
