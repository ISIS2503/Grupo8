package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Dato;
import com.arquisix.sistemaminas.persistence.DatoPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class DatoLogic
{
    @Inject
    private DatoPersistence persistence;

    public Dato create(Long idSensor, Dato entity)
    {
        return persistence.create(idSensor,entity);
    }

    public List<Dato> retrieveAll(Long idSensor)
    {
        return persistence.retrieveAll(idSensor);
    }

    public Dato retrieve(Long idSensor, Long idDato)
    {
        return persistence.retrieve(idSensor, idDato);
    }

    public Dato update(Long idSensor, Long id, Dato entity)
    {
        return persistence.update(id, entity);
    }

    public Dato delete(Long idSensor, Long idDato)
    {
        return persistence.delete(idSensor, idDato);
    }
}
