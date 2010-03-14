package guicymorphic.fw.gwt.common.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;


/**
 * Extends {@link com.google.gwt.event.shared.HandlerManager} with &#64;Inject constructor.
 *
 * @author Alen Vrecko
 */
public class EventBus extends HandlerManager {

    @Inject
    public EventBus() {
        super(null);
    }
}
