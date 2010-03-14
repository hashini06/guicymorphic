package guicymorphic.fw.gwt.common.client;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;

import java.util.HashMap;

/**
 * Default implementation that uses {@link History}.
 *
 * @author Alen Vrecko
 */
public class BrowserHistoryImpl implements BrowserHistory {

    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return History.addValueChangeHandler(handler);
    }

    public void back() {
        History.back();
    }

    public void fireCurrentHistoryState() {
        History.fireCurrentHistoryState();
    }

    public void forward() {
        History.forward();
    }

    public String getToken() {
        return History.getToken();
    }

    public void newItem(String historyToken) {
        History.newItem(historyToken);
    }

    public void newItem(String historyToken, boolean issueEvent) {
        History.newItem(historyToken, issueEvent);
    }

    public void newItem(HistoryItem item) {
        History.newItem(item.toString());
    }

    public void newItem(String id, HashMap<String, String> params) {
        History.newItem(HistoryItem.toString(id, params));
    }

    public void newItem(String id, String key, String value) {
        History.newItem(id + "?" + key + "=" + value);
    }

    public void newItem(String id, String key1, String value1, String key2, String value2) {
        History.newItem(id + "?" + key1 + "=" + value1 + "&" + key2 + "=" + value2);
    }


}
