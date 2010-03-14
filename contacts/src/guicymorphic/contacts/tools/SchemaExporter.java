package guicymorphic.contacts.tools;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wideplay.warp.persist.PersistenceService;
import guicymorphic.contacts.server.cfg.ContactsServerModule;
import guicymorphic.fw.util.Properties2;
import org.apache.log4j.Level;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Properties;

/**
 * Demonstrates how to export hibernate schema.
 *
 * @author Alen Vrecko
 */
public class SchemaExporter {

    public static void main(String[] args) {
        Properties hibernateProperties = Properties2.loadProperties("hibernate-production-db.properties");
        Injector injector = Guice.createInjector(new ContactsServerModule(hibernateProperties));
        Configuration configuration = injector.getInstance(Configuration.class);

//        You can call generate schema directly on the Configuration and use the strings as you please
//        String[] strings = configuration.generateSchemaCreationScript(new HSQLDialect());
        SchemaExport export = new SchemaExport(configuration);

//           This will print on stdout the schema as configured by the hibernate Configuration object
        export.create(true,true);

//        This will get shutdown via the Runtime hook anyway but it is nicer this way
        injector.getInstance(PersistenceService.class).shutdown();
    }
}
