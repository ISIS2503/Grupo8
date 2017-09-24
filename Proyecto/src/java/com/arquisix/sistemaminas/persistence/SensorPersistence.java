package com.arquisix.sistemaminas.persistence;

import com.arquisix.sistemaminas.entities.Sensor;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class SensorPersistence
{
    public Sensor create(Long idMicrocontrolador, Sensor entity)
    {
        return entity;
    }

    public Sensor retrieve(Long id)
    {
        return null;
    }

    public List<Sensor> retrieveAll(Long idMicrocontrolador)
    {
        return null;
    }

    public Sensor update(Long id, Sensor entity)
    {
        return null;
    }

    public Sensor delete(Long id)
    {
        return null;
    }
}
