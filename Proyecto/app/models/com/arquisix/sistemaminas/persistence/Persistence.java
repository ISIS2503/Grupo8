package models.com.arquisix.sistemaminas.persistence;

import javax.inject.*;

import models.com.arquisix.sistemaminas.persistence.DatabaseExecutionContext;
import play.db.*;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Created by juanchaves on 24/09/17.
 */
@Singleton
public class Persistence
{
    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public Persistence(Database db, DatabaseExecutionContext context) {
        this.db = db;
        this.executionContext = executionContext;
    }

    public CompletionStage<Integer> updateSomething() {
        return CompletableFuture.supplyAsync(() -> {
            return db.withConnection(connection -> {
                // do whatever you need with the db connection
                return 1;
            });
        }, executionContext);
    }
}
