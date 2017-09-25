package models.com.arquisix.sistemaminas.entities;

import models.com.arquisix.sistemaminas.entities.*;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Microcontrolador {
    private Long id;
    private List<Sensor> sensores;

    public Microcontrolador(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }
}
