package com.arquisix.sistemaminas.persistence;

import com.arquisix.sistemaminas.entities.Dato;

import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class DatoPersistence {
   public Dato create(Long idSensor, Dato entity)
   {
       return entity;
   }

   public Dato retrieve(Long idSensor, Long idDato)
   {
       return null;
   }

   public List<Dato> retrieveAll(Long idSensor)
   {
       return null;
   }

   public Dato update(Long id, Dato entity)
   {
       return null;
   }

   public Dato delete(Long idSensor, Long idDato)
   {
       return null;
   }
}
