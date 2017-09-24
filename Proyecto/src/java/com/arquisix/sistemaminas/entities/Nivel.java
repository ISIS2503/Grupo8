package com.arquisix.sistemaminas.entities;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Nivel {

    private Long id;
    private Integer nivel;
    private List<Area> areas;

    public Nivel(Long id, Integer nivel) {
        this.id = id;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }
}
