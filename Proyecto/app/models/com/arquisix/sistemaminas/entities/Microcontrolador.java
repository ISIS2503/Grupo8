package models.com.arquisix.sistemaminas.entities;

import models.com.arquisix.sistemaminas.entities.*;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Microcontrolador {
    private Long id;
    private List<com.arquisix.sistemaminas.entities.Sensor> sensores;

    public Microcontrolador(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<com.arquisix.sistemaminas.entities.Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<com.arquisix.sistemaminas.entities.Sensor> sensores) {
        this.sensores = sensores;
    }
}
