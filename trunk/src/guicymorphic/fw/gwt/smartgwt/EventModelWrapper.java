package guicymorphic.fw.gwt.smartgwt;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * Wraps between SmartGwt overly redundant event classes to GWT.
 *
 * @author Alen Vrecko
 */
public class EventModelWrapper {

    public static HasClickHandlers wrap(final ToolStripButton button) {
        return new HasClickHandlers() {
            @Override
            public HandlerRegistration addClickHandler(final ClickHandler clickHandler) {
                return button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        clickHandler.onClick(wrap(clickEvent));
                    }
                });
            }

            @Override
            public void fireEvent(GwtEvent<?> gwtEvent) {
                throw new RuntimeException("Not implemented.");
            }
        };
    }

    private static com.google.gwt.event.dom.client.ClickEvent wrap(ClickEvent clickEvent) {
        return new com.google.gwt.event.dom.client.ClickEvent() {
        };
    }

    public static HasClickHandlers wrap(final IButton button) {
        return new HasClickHandlers() {
            @Override
            public HandlerRegistration addClickHandler(final ClickHandler clickHandler) {
                final HandlerRegistration registration = button.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        clickHandler.onClick(null);
                    }
                });

                return new HandlerRegistration() {
                    @Override
                    public void removeHandler() {
                        registration.removeHandler();
                    }
                };
            }

            @Override
            public void fireEvent(GwtEvent<?> gwtEvent) {
                throw new RuntimeException("Not implemented.");
            }
        };
    }

    public static HasValue<String> wrap(final TextItem item) {
        return new HasValue<String>() {
            @Override
            public String getValue() {
                return (String) item.getValue();
            }

            @Override
            public void setValue(String s) {
                item.setValue(s);
            }

            @Override
            public void setValue(String s, boolean b) {
                throw new RuntimeException("Not implemented.");
            }

            @Override
            public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> stringValueChangeHandler) {
                throw new RuntimeException("Not implemented.");
            }

            @Override
            public void fireEvent(GwtEvent<?> gwtEvent) {
                throw new RuntimeException("Not implemented.");
            }
        };
    }
}
