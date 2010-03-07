package guicymorphic.webapp.contacts.server.cfg;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import guicymorphic.fw.dal.hibernate.ExtendedAnnotationConfiguration;
import guicymorphic.webapp.contacts.server.entity.ContactEntity;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 11:12:22 AM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsHibernateModule extends AbstractModule {
    private final Properties properties;

    public ContactsHibernateModule(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure() {
        // using hibernate and transactions around @Transactional methods
        install(PersistenceService.usingHibernate().across(UnitOfWork.TRANSACTION).buildModule());

        // using the extended annotation configuration which allows us the add all entities from a package
        bind(Configuration.class).toInstance(
                ExtendedAnnotationConfiguration.fromProperties(properties).scanPackageForAnnotatedClasses(
                        ContactEntity.class.getPackage()));

    }
}
