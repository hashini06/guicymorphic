package guicymorphic.examples.gwt.mvp.google;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Gwt style presenter.
 *
 * @author Alen Vrecko
 */
public class GwtTempConverterPresenter implements Presenter {

    private final View view;

    // 1. Ask yourself what hooks will you need from the view?

    public interface View {

        // we will want to be notified when the user changes the celsius text box
        // note: we have directly influenced the view as we require a _value change_ handler
        // it is around in dotNet way. The presenter exposes methods to the view

        HasValueChangeHandlers<String> celsiusText();

        HasValueChangeHandlers<String> fahrenheitText();

        // we need a way to update the view

        void setCelsiusText(String value);

        void setFahrenheitText(String value);


        // For using the View as a widget when we put in the container

        Widget asWidget();

    }

    // 2. Create the constructor which gets all the dependencies
    // You can use @Inject and GIN for this
    // note: dotNet approach both the View and the Presenter need to know about each other

    public GwtTempConverterPresenter(View view) {
        this.view = view;

        // install the hooks into the view
        bind();
    }

    private void bind() {
        // 3. Install hooks in the view
        view.celsiusText().addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                celsiusValueChanged(event.getValue());
            }
        });

        view.fahrenheitText().addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                fahrenheitValueChanged(event.getValue());
            }
        });

    }


    // code logic

    private void fahrenheitValueChanged(String value) {
        try {
            int fahrenheitValue = Integer.parseInt(value);
            int celsiusValue = (int) Math.round(((fahrenheitValue - 32.0) / 9.0) * 5.0);
            view.setCelsiusText(String.valueOf(celsiusValue));
        } catch (NumberFormatException e) {
            // can't really do anything
            view.setCelsiusText("N/A");
        }

    }

    private void celsiusValueChanged(String value) {
        try {
            int celsiusValue = Integer.parseInt(value);
            int fahrenheitValue = (int) Math.round(((celsiusValue * 9.0) / 5.0) + 32.0);
            view.setFahrenheitText("" + fahrenheitValue);
        } catch (NumberFormatException e) {
            // can't really do anything
            view.setFahrenheitText("N/A");
        }
    }


    // pass the putting the view in the container through the presenter

    public void go(HasWidgets widgets) {
        widgets.clear();
        widgets.add(view.asWidget());
    }

}
