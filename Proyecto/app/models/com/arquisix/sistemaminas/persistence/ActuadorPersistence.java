package models.com.arquisix.sistemaminas.persistence;

import models.com.arquisix.sistemaminas.entities.Actuador;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by juanchaves on 24/09/17.
 */
public class ActuadorPersistence
{
    public Actuador retrieve(Long id)
    {
        return null;
    }

    public List<Actuador> retrieveAll(Long idArea)
    {
        return null;
    }

    public Actuador update(Long id, Actuador entity)
    {
        return null;
    }

    public Actuador delete(Long id)
    {
        return null;
    }
}
