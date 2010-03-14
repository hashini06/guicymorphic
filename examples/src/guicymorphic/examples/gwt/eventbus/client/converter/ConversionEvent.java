package guicymorphic.examples.gwt.eventbus.client.converter;

import com.google.gwt.event.shared.GwtEvent;

public class ConversionEvent extends GwtEvent<ConversionEventHandler> {
    public static Type<ConversionEventHandler> TYPE = new Type<ConversionEventHandler>();
    private final String celsius;
    private final String fahrenheit;


    protected ConversionEvent(String celsius,String fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }

    @Override
    public Type<ConversionEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ConversionEventHandler handler) {
        handler.onConversion(this);
    }


    public String getCelsius() {
        return celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }
}
