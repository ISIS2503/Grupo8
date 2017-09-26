/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Ciudad.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 * <p>
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;

/**
 * Clase que representa una ciudad en el sistema
 * Created by scvalencia606 on 02/08/2015.
 */

@Entity
public class Ciudad extends Model
{

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Nombre de la ciudad
     */
    private String nombre;

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor de la clase (sin argumentos)
     */
    public Ciudad()
    {
    }

    /**
     * Constructor de la clase (con argumentos)
     *
     * @param nombre
     */
    public Ciudad(String nombre)
    {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Getters & Setters
    //-----------------------------------------------------------

    /**
     * Devuelve el nombre de la ciudad
     *
     * @return nombre Nombre de la ciudad
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Modifica el nombre de la ciudad
     *
     * @param nombre Nuevo nombre de la ciudad
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Métodos auxiliares
    //-----------------------------------------------------------

    /**
     * Crea un objeto Ciudad apartir de un nodo Json
     *
     * @param j Nodo Json con atributos y valores de un objeto Ciudad
     */
    public static Ciudad bind(JsonNode j)
    {
        String nombre = j.findPath("nombre").asText();
        Ciudad ciudad = new Ciudad(nombre);
        return ciudad;
    }
}
