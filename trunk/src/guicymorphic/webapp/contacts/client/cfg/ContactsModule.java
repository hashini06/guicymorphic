package guicymorphic.webapp.contacts.client.cfg;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import guicymorphic.webapp.contacts.client.event.EventBus;
import guicymorphic.webapp.contacts.client.presenter.ContactsPresenter;
import guicymorphic.webapp.contacts.client.presenter.EditContactPresenter;
import guicymorphic.webapp.contacts.client.view.ContactsView;
import guicymorphic.webapp.contacts.client.view.EditContactView;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 7:52:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsModule extends AbstractGinModule {
    @Override
    protected void configure() {
        // bind event bus
        bind(EventBus.class).in(Singleton.class);
        // no need to bind RPC services are they are bound by default

        // bind the views
        bind(ContactsPresenter.Display.class).to(ContactsView.class);
        bind(EditContactPresenter.Display.class).to(EditContactView.class);
    }
}
