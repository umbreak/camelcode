package modules;

import java.net.UnknownHostException;
import models.Model;
import models.PostcodeUnit;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.mapping.DefaultCreator;
import play.Application;
import play.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * @author Mathias Bogaert
 */
public class MorphiaModule extends AbstractModule {
    @Override
    protected void configure() {
        requireBinding(Application.class);
        requestStaticInjection(Model.class);
    }

    @Provides
    Morphia createMorphia(final Application application) {
        Morphia morphia = new Morphia();
        morphia.getMapper().getOptions().objectFactory = new DefaultCreator() {
            @Override
            protected ClassLoader getClassLoaderForClass(String clazz, DBObject object) {
                return application.classloader();
            }
        };

        morphia.map(PostcodeUnit.class);
        Logger.info("Morhpia instance created");
        new ValidationExtension(morphia);
        return morphia;
    }

    @Provides
    Datastore createDatastore(Mongo mongo, Morphia morphia, final Application application) {
        Datastore datastore = morphia.createDatastore(
                mongo,
                application.configuration().getString("mongodb.db"));
        datastore.ensureIndexes();

        Logger.info("Mongo Linked with Morphia");
        return datastore;
    }

    @Provides @Singleton
    Mongo create(final Application application) {
        try {
        	Mongo mongo= new MongoClient(application.configuration().getString("mongodb.url"));
        	mongo.getDB(application.configuration().getString("mongodb.db")).authenticate(
        			application.configuration().getString("mongodb.username"),
        			application.configuration().getString("mongodb.password").toCharArray());
            Logger.info("Connected to MongoDB [" + mongo.debugString() + "] database [" + application.configuration().getString("mongodb.db") + "]");
        	return mongo;
        } catch (UnknownHostException e) {
            addError(e);
            return null;
        }
    }
}