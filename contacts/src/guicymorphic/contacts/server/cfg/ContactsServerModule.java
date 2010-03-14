package guicymorphic.contacts.server.cfg;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import guicymorphic.contacts.server.bl.ContactsBl;
import guicymorphic.contacts.server.entity.ContactEntity;
import guicymorphic.contacts.shared.rpc.ContactsService;
import guicymorphic.fw.dal.hibernate.ExtendedAnnotationConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 3:46:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsServerModule extends AbstractModule {
    private final Properties hibernateProperties;


    public ContactsServerModule(Properties hibernateProperties) {
        this.hibernateProperties = hibernateProperties;
    }

    @Override
    protected void configure() {
           // bind Service to Business Logic
                bind(ContactsService.class).to(ContactsBl.class).in(Singleton.class);


                // using hibernate and transactions around @Transactional methods
                install(PersistenceService.usingHibernate().across(UnitOfWork.TRANSACTION).buildModule());

                // using the extended annotation configuration which allows us the add all entities from a package
                bind(Configuration.class).toInstance(
                        ExtendedAnnotationConfiguration.fromProperties(hibernateProperties).scanPackageForAnnotatedClasses(
                                ContactEntity.class.getPackage()));



                // set up dozer
                System.setProperty("dozer.configuration", "dozer.properties");
                DozerBeanMapper mapper = new DozerBeanMapper();
                mapper.setMappingFiles(Arrays.asList("dozer-global-configuration.xml",
                        "ContactDisplayDto-ContactEntity.dozer.xml"));
                bind(Mapper.class).toInstance(mapper);
    }
}
