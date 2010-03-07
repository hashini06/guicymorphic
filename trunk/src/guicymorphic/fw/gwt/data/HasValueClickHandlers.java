package guicymorphic.fw.gwt.data;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 6:58:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HasValueClickHandlers<V> {

    HandlerRegistration addValueClickHandler(ValueClickHandler<V> handler);
}
