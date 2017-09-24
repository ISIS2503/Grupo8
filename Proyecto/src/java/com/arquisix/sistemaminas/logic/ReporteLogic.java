package com.arquisix.sistemaminas.logic;

import com.arquisix.sistemaminas.entities.Reporte;
import com.arquisix.sistemaminas.persistence.ReportePersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class ReporteLogic
{
    @Inject
    private ReportePersistence persistence;

    public Reporte create(Reporte entity)
    {
        return persistence.create(entity);
    }

    public List<Reporte> retrieveAll()
    {
        return persistence.retrieveAll();
    }

    public Reporte retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public Reporte update(Long id, Reporte entity)
    {
        return persistence.update(id, entity);
    }

    public Reporte delete(Long id)
    {
        return persistence.delete(id);
    }
}
