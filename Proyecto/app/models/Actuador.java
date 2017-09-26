package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class Actuador extends Model
{
    private Long id;

    private Boolean activo;

    private ZonedDateTime inicio;

    public Actuador()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Boolean getActivo()
    {
        return activo;
    }

    public void setActivo(Boolean activo)
    {
        this.activo = activo;
    }

    public ZonedDateTime getInicio()
    {
        return inicio;
    }

    public void setInicio(ZonedDateTime inicio)
    {
        this.inicio = inicio;
    }

    public static Actuador bind(JsonNode json)
    {
        Actuador actuador = new Actuador();
        actuador.id = json.findPath("id").asLong();
        actuador.activo = json.findPath("activo").asBoolean();
        Instant i = Instant.ofEpochSecond(json.findPath("inicio").asLong());
        actuador.inicio = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
        return actuador;
    }

}
