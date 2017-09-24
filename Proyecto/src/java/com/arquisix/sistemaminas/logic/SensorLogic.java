package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Sensor;
import com.arquisix.sistemaminas.persistence.SensorPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class SensorLogic
{
    @Inject
    private SensorPersistence persistence;

    public Sensor create(Long idMicrocontrolador, Sensor entity)
    {
        return persistence.create(idMicrocontrolador,entity);
    }

    public List<Sensor> retrieveAll(Long idMicrocontrolador)
    {
        return persistence.retrieveAll(idMicrocontrolador);
    }

    public Sensor retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Sensor update(Long idMicrocontrolador, Long id, Sensor entity)
    {
        return persistence.update(id, entity);
    }

    public Sensor delete(Long idMicrocontrolador, Long idSensor)
    {
        return persistence.delete(idSensor);
    }
}
