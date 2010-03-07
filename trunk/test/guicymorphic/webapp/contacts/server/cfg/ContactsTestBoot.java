package guicymorphic.webapp.contacts.server.cfg;

import com.google.inject.AbstractModule;
import guicymorphic.fw.util.Properties2;
import guicymorphic.webapp.contacts.server.cfg.dozer.ContactsDozerModule;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 12:28:09 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsTestBoot extends AbstractModule {
    @Override
    protected void configure() {
        install(
                new ContactsHibernateModule(Properties2.loadProperties("hibernate-test-db.properties", ContactsTestBoot.class))
        );
        install(new ContactsDozerModule());
    }
}
