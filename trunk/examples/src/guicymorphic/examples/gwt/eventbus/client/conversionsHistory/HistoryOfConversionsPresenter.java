package guicymorphic.examples.gwt.eventbus.client.conversionsHistory;

import com.google.gwt.event.shared.HandlerRegistration;
import guicymorphic.fw.gwt.common.client.EventBus;
import guicymorphic.examples.gwt.eventbus.client.converter.ConversionEvent;
import guicymorphic.examples.gwt.eventbus.client.converter.ConversionEventHandler;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 8:44:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoryOfConversionsPresenter {
    private final EventBus bus;
    private HistoryOfConversionsView view;
    private HandlerRegistration registration;

    public interface View{
        void add(String celsius, String fahrenheit);
    }

    public HistoryOfConversionsPresenter(EventBus bus) {
        this.bus = bus;
    }

    public void bind(final HistoryOfConversionsView view) {
         this.view = view;
         bus.addHandler(ConversionEvent.TYPE, new ConversionEventHandler() {
            public void onConversion(ConversionEvent event) {
                view.add(event.getCelsius(), event.getFahrenheit());
            }
        });
    }
}
