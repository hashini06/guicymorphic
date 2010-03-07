package guicymorphic.webapp.contacts.server.cfg.dozer;

import com.google.inject.AbstractModule;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 12:35:54 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsDozerModule extends AbstractModule {

    @Override
    protected void configure() {
        System.setProperty("dozer.configuration", "guicymorphic/webapp/contacts/server/cfg/dozer/dozer.properties");
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("guicymorphic/webapp/contacts/server/cfg/dozer/dozer-global-configuration.xml",
                "guicymorphic/webapp/contacts/server/cfg/dozer/ContactDisplayDto-ContactEntity.dozer.xml"));
        bind(Mapper.class).toInstance(mapper);
    }

}
