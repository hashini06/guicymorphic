package guicymorphic.webapp.contacts.client.event;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 8:03:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventBus extends HandlerManager {

    @Inject
    public EventBus() {
        super(null);
    }
}
