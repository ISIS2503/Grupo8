package models.com.arquisix.sistemaminas.entities;

import models.com.arquisix.sistemaminas.entities.Actuador;
import models.com.arquisix.sistemaminas.entities.Alerta;
import models.com.arquisix.sistemaminas.entities.Microcontrolador;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Area {

    private Long id;
    private Integer tipo;
    private List<Actuador> actuadores;
    private List<Alerta> alertas;
    private List<Microcontrolador> microcontroladores;

    public Area(Long id, Integer tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public List<Actuador> getActuadores() {
        return actuadores;
    }

    public void setActuadores(List<Actuador> actuadores) {
        this.actuadores = actuadores;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    public List<Microcontrolador> getMicrocontroladores() {
        return microcontroladores;
    }

    public void setMicrocontroladores(List<Microcontrolador> microcontroladores) {
        this.microcontroladores = microcontroladores;
    }
}
