package guicymorphic.webapp.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.webapp.contacts.client.cfg.ContactsGinjector;

public class Contacts implements EntryPoint {

    public void onModuleLoad() {
        // create ginjector via gwt
        ContactsGinjector injector = GWT.create(ContactsGinjector.class);
        // get app controller via gin
        AppController appController = injector.getAppController();
        // put the show on the road
        appController.go(RootPanel.get());
    }
}
