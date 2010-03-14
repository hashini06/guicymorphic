package guicymorphic.examples.gwt.eventbus.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import guicymorphic.fw.gwt.common.client.BrowserHistoryImpl;
import guicymorphic.fw.gwt.common.client.EventBus;
import guicymorphic.fw.gwt.common.client.HistoryItem;
import guicymorphic.examples.gwt.eventbus.client.conversionsHistory.HistoryOfConversionsPresenter;
import guicymorphic.examples.gwt.eventbus.client.conversionsHistory.HistoryOfConversionsView;
import guicymorphic.examples.gwt.eventbus.client.converter.EventTempConverterPresenter;
import guicymorphic.examples.gwt.eventbus.client.converter.EventTempConverterView;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 8:43:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventBusExample implements EntryPoint, ValueChangeHandler<String> {
    private final EventBus eventBus = new EventBus();
    private final EventTempConverterPresenter presenter = new EventTempConverterPresenter(eventBus,new BrowserHistoryImpl());
    private final EventTempConverterView converterView = new EventTempConverterView(presenter);
    public static final String CONVERT = "convert";

    public void onModuleLoad() {
        VerticalPanel panel = new VerticalPanel();
        panel.add(converterView);
        panel.add(new HistoryOfConversionsView(new HistoryOfConversionsPresenter(eventBus)));
        History.addValueChangeHandler(this);
        RootPanel.get().add(panel);
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        HistoryItem item = HistoryItem.parse(event.getValue());
        if (CONVERT.equalsIgnoreCase(item.getId())) {
            presenter.onHistoryEvent(item);
        }
    }
}
