package guicymorphic.security;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import guicymorphic.fw.dal.hibernate.ExtendedAnnotationConfiguration;
import guicymorphic.fw.util.Properties2;
import guicymorphic.security.entity.Marker;
import guicymorphic.security.entity.acl.AclEntryEntity;
import guicymorphic.security.entity.acl.AclTypeLinkEntity;
import guicymorphic.security.hibernate.AclHibernate;
import guicymorphic.security.hibernate.AclHibernateRepository;
import guicymorphic.security.hibernate.AclHibernateRepositoryTest;
import guicymorphic.security.hibernate.AclHibernateServiceImpl;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.hibernate.cfg.Configuration;
import org.hsqldb.Server;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 11:23:19
 * To change this template use File | Settings | File Templates.
 */
public class AllTests {

    public static Test suite() {
        final Server server = new Server();

        TestSuite suite = new GuiceTestSuite(getInjector()) {
            @Override
            public void setUp(Injector injector) {
                new Thread(new Runnable() {
                    public void run() {
                        server.setSilent(true);
                        server.setDatabasePath(0, "mem:acl");
                        server.setDatabaseName(0, "acl");
                        server.start();
                    }
                }).start();
                injector.getInstance(PersistenceService.class).start();
            }

            @Override
            public void tearDown(Injector injector) {
                injector.getInstance(PersistenceService.class).shutdown();
                server.stop();
            }
        };

        suite.addTestSuite(AclHibernateRepositoryTest.class);
        suite.addTestSuite(AclHibernateServiceImpl.class);
        return suite;
    }


    public static Injector getInjector() {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                System.out.println("Get injector");
                install(PersistenceService.usingHibernate().across(UnitOfWork.TRANSACTION).buildModule());
                bind(Configuration.class).toInstance(
                        ExtendedAnnotationConfiguration.fromProperties(Properties2.loadProperties("hibernate.properties")).scanPackageForAnnotatedClasses(
                                Marker.class.getPackage()));

                install(AclHibernate.buildModule(AclEntryEntity.class, AclTypeLinkEntity.class));
                bind(EntityRepository.class).to(AclHibernateRepository.class);
                bind(UserRepository.class).to(AclHibernateRepository.class);

            }
        });
    }
}
