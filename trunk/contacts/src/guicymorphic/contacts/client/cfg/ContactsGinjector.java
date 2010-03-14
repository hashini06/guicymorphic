package guicymorphic.contacts.client.cfg;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import guicymorphic.contacts.client.AppController;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 7:50:27 PM
 * To change this template use File | Settings | File Templates.
 */
@GinModules(ContactsGinModule.class)
public interface ContactsGinjector extends Ginjector {


    AppController getController();

    
}
