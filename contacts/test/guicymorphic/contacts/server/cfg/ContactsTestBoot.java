package guicymorphic.contacts.server.cfg;

import com.google.inject.AbstractModule;
import guicymorphic.fw.util.Properties2;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 3:49:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsTestBoot extends AbstractModule {

    @Override
    protected void configure() {
        // get test hibernate properties
        Properties hibernateProps = Properties2.loadProperties("hibernate-test-db.properties", ContactsTestBoot.class);
        install(new ContactsServerModule(hibernateProps));

        // that's it we don't need anything else
    }
}
