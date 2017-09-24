package com.arquisix.sistemaminas.entities;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Alerta {


    private Long id;
    private Integer tipo;

    public Alerta(Long id, Integer tipo) {
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

}
