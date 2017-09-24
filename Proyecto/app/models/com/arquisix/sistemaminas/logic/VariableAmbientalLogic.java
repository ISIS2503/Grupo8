package models.com.arquisix.sistemaminas.logic;

import models.com.arquisix.sistemaminas.entities.VariableAmbiental;
import models.com.arquisix.sistemaminas.persistence.VariableAmbientalPersistence;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by juanchaves on 24/09/17.
 */
public class VariableAmbientalLogic
{
    @Inject
    private VariableAmbientalPersistence persistence;

    public VariableAmbiental create(VariableAmbiental entity)
    {
        return persistence.create(entity);
    }

    public List<VariableAmbiental> retrieveAll()
    {
        return persistence.retrieveAll();
    }

    public VariableAmbiental retrieve(Long id)
    {
        return persistence.retrieve(id);
    }

    public VariableAmbiental update(Long id, VariableAmbiental entity)
    {
        return persistence.update(id, entity);
    }

    public VariableAmbiental delete(Long id)
    {
        return persistence.delete(id);
    }
}
