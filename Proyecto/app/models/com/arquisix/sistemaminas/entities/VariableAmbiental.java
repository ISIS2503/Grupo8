package models.com.arquisix.sistemaminas.entities;

/**
 * Created by juanchaves on 24/09/17.
 */
public class VariableAmbiental {
    private Float valorMaximo;
    private Float valorMinimo;

    public VariableAmbiental(Float valorMaximo, Float valorMinimo, Float variacion, String uniadadDeMedida, Float precision, Float frecuencia) {
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.variacion = variacion;
        this.uniadadDeMedida = uniadadDeMedida;
        this.precision = precision;
        this.frecuencia = frecuencia;
    }

    public Float getValorMaximo() {

        return valorMaximo;
    }

    public void setValorMaximo(Float valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public Float getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(Float valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public Float getVariacion() {
        return variacion;
    }

    public void setVariacion(Float variacion) {
        this.variacion = variacion;
    }

    public String getUniadadDeMedida() {
        return uniadadDeMedida;
    }

    public void setUniadadDeMedida(String uniadadDeMedida) {
        this.uniadadDeMedida = uniadadDeMedida;
    }

    public Float getPrecision() {
        return precision;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    public Float getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Float frecuencia) {
        this.frecuencia = frecuencia;
    }

    private Float variacion;
    private String uniadadDeMedida;
    private Float precision;
    private Float frecuencia;
}
