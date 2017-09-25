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
    private List<Nivel> niveles;
    private List<Reporte> reportes;
    private  List<Usuario> usuarios;

    public SistemaMinas(Map<Long, Long[]> mapaSensores) {
        this.mapaSensores = mapaSensores;
    }

    public Map<Long, Long[]> getMapaSensores() {
        return mapaSensores;
    }

    public void setMapaSensores(Map<Long, Long[]> mapaSensores) {
        this.mapaSensores = mapaSensores;
    }

    public List<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivel> niveles) {
        this.niveles = niveles;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
