package com.arquisix.sistemaminas.entities;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Sensor {

    private Long id;
    private Float minimo;
    private Float maximo;
    private VariableAmbiental tipo;
    private List<Dato> datos;

    public Sensor(Long id, Float minimo, Float maximo, VariableAmbiental tipo) {
        this.id = id;
        this.minimo = minimo;
        this.maximo = maximo;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMinimo() {
        return minimo;
    }

    public void setMinimo(Float minimo) {
        this.minimo = minimo;
    }

    public Float getMaximo() {
        return maximo;
    }

    public void setMaximo(Float maximo) {
        this.maximo = maximo;
    }

    public VariableAmbiental getTipo() {
        return tipo;
    }

    public void setTipo(VariableAmbiental tipo) {
        this.tipo = tipo;
    }

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }
}
