package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;

@Entity
public class VariableAmbiental extends Model
{
    private Float valorMaximo;

    private Float valorMinimo;

    private Float variacion;

    private String uniadadDeMedida;

    private Float precision;

    private Float frecuencia;

    public VariableAmbiental()
    {
    }

    public Float getValorMaximo()
    {

        return valorMaximo;
    }

    public void setValorMaximo(Float valorMaximo)
    {
        this.valorMaximo = valorMaximo;
    }

    public Float getValorMinimo()
    {
        return valorMinimo;
    }

    public void setValorMinimo(Float valorMinimo)
    {
        this.valorMinimo = valorMinimo;
    }

    public Float getVariacion()
    {
        return variacion;
    }

    public void setVariacion(Float variacion)
    {
        this.variacion = variacion;
    }

    public String getUniadadDeMedida()
    {
        return uniadadDeMedida;
    }

    public void setUniadadDeMedida(String uniadadDeMedida)
    {
        this.uniadadDeMedida = uniadadDeMedida;
    }

    public Float getPrecision()
    {
        return precision;
    }

    public void setPrecision(Float precision)
    {
        this.precision = precision;
    }

    public Float getFrecuencia()
    {
        return frecuencia;
    }

    public void setFrecuencia(Float frecuencia)
    {
        this.frecuencia = frecuencia;
    }

    public static VariableAmbiental bind(JsonNode json)
    {
        VariableAmbiental var = new VariableAmbiental();
        var.valorMaximo = json.findPath("valorMaximo").floatValue();
        var.valorMinimo = json.findPath("valorMinimo").floatValue();
        var.variacion = json.findPath("variacion").floatValue();
        var.uniadadDeMedida = json.findPath("unidadDeMedida").asText();
        var.precision = json.findPath("precision").floatValue();
        var.frecuencia = json.findPath("frecuencia").floatValue();
        return var;
    }
}