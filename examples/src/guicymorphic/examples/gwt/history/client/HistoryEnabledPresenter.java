package guicymorphic.examples.gwt.history.client;

import guicymorphic.fw.gwt.common.client.HistoryItem;


/**
 * A sample of base interface for all presenters. Not really needed but just to point out you can use some base presenter stuff.
 *
 * @author Alen Vrecko
 */
public interface HistoryEnabledPresenter {

    void onHistoryEvent(HistoryItem item);
}
