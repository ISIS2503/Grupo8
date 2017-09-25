package models.com.arquisix.sistemaminas.entities;

import models.com.arquisix.sistemaminas.entities.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Reporte {
    private ZonedDateTime fecha;
    private List<Nivel> niveles;
    public Reporte(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public ZonedDateTime getFecha() {
        return fecha;
    }

    public void setFecha(ZonedDateTime fecha) {
        this.fecha = fecha;
    }

    public Reporte(ZonedDateTime fecha, List<Nivel> niveles) {
        this.fecha = fecha;
        this.niveles = niveles;
    }
}
