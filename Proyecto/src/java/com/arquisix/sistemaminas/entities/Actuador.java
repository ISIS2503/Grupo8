package com.arquisix.sistemaminas.entities;

import java.time.ZonedDateTime;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Actuador {
    private Long id;
    private Boolean activo;
    private ZonedDateTime inicio;

    public Actuador(Long id, Boolean activo, ZonedDateTime inicio) {
        this.id = id;
        this.activo = activo;
        this.inicio = inicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public ZonedDateTime getInicio() {
        return inicio;
    }

    public void setInicio(ZonedDateTime inicio) {
        this.inicio = inicio;
    }

}
