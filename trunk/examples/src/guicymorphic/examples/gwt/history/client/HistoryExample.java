package guicymorphic.examples.gwt.history.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.fw.gwt.common.client.BrowserHistoryImpl;
import guicymorphic.fw.gwt.common.client.HistoryItem;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 7:36:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoryExample implements EntryPoint, ValueChangeHandler<String> {
    // centralized list of all history bound views/presenters
    public static final String CONVERT = "convert";
    
    private final HistoryEnabledTempConverterView view = new HistoryEnabledTempConverterView(new HistoryEnabledTempConverterPresenter(new BrowserHistoryImpl()) );

    public void onModuleLoad() {

        // register this to listen to history change events
        History.addValueChangeHandler(this);

        RootPanel.get().add(view);

    }

    // this gets called on history change event fired from the presenter onValueChange
    public void onValueChange(ValueChangeEvent<String> historyEvent) {
        // parse the token to something with higher level api
        HistoryItem historyItem = HistoryItem.parse(historyEvent.getValue());

        // if the id in the history token is for the view then make the presenter do some more stuff
        if (CONVERT.equalsIgnoreCase(historyItem.getId())) {
            // let the presenter handle the history item stuff
            view.getPresenter().onHistoryEvent(historyItem);
        }
    }

}
