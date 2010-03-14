package guicymorphic.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.contacts.client.cfg.ContactsGinjector;

/**
 * The main control point for the web app.
 *
 * @author Alen Vrecko
 */
public class Contacts implements EntryPoint {


    public void onModuleLoad() {

        ContactsGinjector ginjector = GWT.create(ContactsGinjector.class);

        ginjector.getController().go(RootPanel.get());


    }
}
