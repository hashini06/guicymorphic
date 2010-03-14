package guicymorphic.contacts.server.cfg;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.wideplay.warp.persist.PersistenceService;
import guicymorphic.contacts.server.bl.PopulatorBl;
import guicymorphic.fw.service.GuiceRemoteServiceServlet;
import guicymorphic.fw.util.Properties2;

import javax.servlet.ServletContextEvent;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 4:34:59 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsBoot extends GuiceServletContextListener {


    private Injector injector;
    private static final int DEFAULT_POPULATION_SIZE = 100;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {

                Properties properties = Properties2.loadProperties("hibernate-production-db.properties");

                // install everything short of the servlet integration using provided hibernate properties
                install(new ContactsServerModule(properties));

                // set up gwt-guice integration servlet
                serveRegex(".*_rpc$").with(GuiceRemoteServiceServlet.class);

            }
        });

        // start persistence i.e. start the session factory
        injector.getInstance(PersistenceService.class).start();
        // populate the in-memory hsqldb database with some stuff
        injector.getInstance(PopulatorBl.class).populate(DEFAULT_POPULATION_SIZE);
        super.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // make sure to shut down the session factory
        injector.getInstance(PersistenceService.class).shutdown();
        injector = null;
        super.contextDestroyed(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }
}
