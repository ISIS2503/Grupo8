package models.com.arquisix.sistemaminas.entities;

import models.com.arquisix.sistemaminas.entities.*;
import models.com.arquisix.sistemaminas.entities.Reporte;
import models.com.arquisix.sistemaminas.entities.Usuario;

import java.util.List;
import java.util.Map;

/**
 * Created by juanchaves on 24/09/17.
 */
public class SistemaMinas {

    private Map<Long, Long[]> mapaSensores;
    private List<com.arquisix.sistemaminas.entities.Nivel> niveles;
    private List<com.arquisix.sistemaminas.entities.Reporte> reportes;
    private  List<com.arquisix.sistemaminas.entities.Usuario> usuarios;

    public SistemaMinas(Map<Long, Long[]> mapaSensores) {
        this.mapaSensores = mapaSensores;
    }

    public Map<Long, Long[]> getMapaSensores() {
        return mapaSensores;
    }

    public void setMapaSensores(Map<Long, Long[]> mapaSensores) {
        this.mapaSensores = mapaSensores;
    }

    public List<com.arquisix.sistemaminas.entities.Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<com.arquisix.sistemaminas.entities.Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<com.arquisix.sistemaminas.entities.Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<com.arquisix.sistemaminas.entities.Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
