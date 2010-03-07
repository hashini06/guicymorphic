package guicymorphic.webapp.contacts.server.cfg;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.wideplay.warp.persist.PersistenceService;
import guicymorphic.fw.util.Properties2;
import guicymorphic.webapp.contacts.server.bl.PopulatorBl;
import guicymorphic.webapp.contacts.server.cfg.dozer.ContactsDozerModule;

import javax.servlet.ServletContextEvent;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 4:34:59 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsBoot extends GuiceServletContextListener {


    private Injector injector;
    private static final int DEFAULT_POPULATION_SIZE = 100;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        injector = Guice.createInjector(new ContactsHibernateModule(Properties2.loadProperties("hibernate-production-db.properties", ContactsBoot.class)), new ContactsServiceModule(), new ContactsDozerModule());
        injector.getInstance(PersistenceService.class).start();
        injector.getInstance(PopulatorBl.class).populate(DEFAULT_POPULATION_SIZE);
        super.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        injector.getInstance(PersistenceService.class).shutdown();
        injector = null;
        super.contextDestroyed(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return injector;
    }
}
