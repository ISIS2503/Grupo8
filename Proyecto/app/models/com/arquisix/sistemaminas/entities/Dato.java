package models.com.arquisix.sistemaminas.entities;

import java.time.ZonedDateTime;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Dato {
    private Long id;
    private Float valor;
    private ZonedDateTime timeStamp;

    public Dato(Long id, Float valor, ZonedDateTime timeStamp) {
        this.id = id;
        this.valor = valor;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
