package guicymorphic.examples.gwt.eventbus.client.converter;

import com.google.gwt.event.shared.EventHandler;

public interface ConversionEventHandler extends EventHandler {
    void onConversion(ConversionEvent event);
}
