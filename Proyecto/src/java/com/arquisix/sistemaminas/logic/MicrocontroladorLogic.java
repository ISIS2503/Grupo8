package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Microcontrolador;
import com.arquisix.sistemaminas.persistence.MicrocontroladorPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class MicrocontroladorLogic
{
    @Inject
    private MicrocontroladorPersistence persistence;

    public Microcontrolador create(Long idArea, Microcontrolador entity)
    {
        return persistence.create(idArea,entity);
    }

    public List<Microcontrolador> retrieveAll(Long idArea)
    {
        return persistence.retrieveAll(idArea);
    }

    public Microcontrolador retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Microcontrolador update(Long idArea, Long id, Microcontrolador entity)
    {
        return persistence.update(id, entity);
    }

    public Microcontrolador delete(Long idArea,Long idMicrocontrolador)
    {
        return persistence.delete(idMicrocontrolador);
    }
}
