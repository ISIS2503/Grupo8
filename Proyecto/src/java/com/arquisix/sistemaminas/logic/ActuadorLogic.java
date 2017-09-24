package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Actuador;
import com.arquisix.sistemaminas.exceptions.BusinessLogicException;
import com.arquisix.sistemaminas.persistence.ActuadorPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class ActuadorLogic {

    @Inject
    private ActuadorPersistence persistence;

    public void validar(Actuador actuador) throws BusinessLogicException
    {

    }

    public Actuador create(Long idArea,Actuador entity)
    {
        return persistence.create(idArea, entity);
    }

    public List<Actuador> retrieveAll(Long idArea)
    {
        return persistence.retrieveAll(idArea);
    }

    public Actuador retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Actuador update(Long idArea, Long id, Actuador entity)
    {
        return persistence.update(id, entity);
    }

    public Actuador delete(Long idArea, Long idActuador)
    {
        return persistence.delete(idActuador);
    }
}
