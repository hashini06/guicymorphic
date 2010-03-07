package guicymorphic.webapp.contacts.server.cfg;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import guicymorphic.fw.service.gwt.GuiceRemoteServiceServlet;
import guicymorphic.webapp.contacts.server.bl.ContactsBl;
import guicymorphic.webapp.contacts.shared.rpc.ContactsService;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 4:34:05 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsServiceModule extends ServletModule {

    @Override
    protected void configureServlets() {
        serveRegex(".*_rpc").with(GuiceRemoteServiceServlet.class);
        bind(ContactsService.class).to(ContactsBl.class).in(Singleton.class);
    }
}
