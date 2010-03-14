package guicymorphic.examples.gwt.history.client;

import guicymorphic.fw.gwt.common.client.BrowserHistory;
import guicymorphic.fw.gwt.common.client.HistoryItem;


/**
 * Dot net style presenter example.
 *
 * @author Alen Vrecko
 */
public class HistoryEnabledTempConverterPresenter implements HistoryEnabledPresenter{


    private String celsius;
    private String fahrenheit;
    private final BrowserHistory history;

    public interface View {

        // note: we only specify what we will do on the view
        // we don't need any hooks from the view as the view will call
        // methods on the presenter _directly_

        void setCelsiusText(String value);

        void setFahrenheitText(String value);
    }

    // 2. Here we would get dependencies such as EventBus and Services but NOT the view

    public HistoryEnabledTempConverterPresenter(BrowserHistory history) {
        this.history = history;
    }

    private View view;

    // note that the view will install itself on the presenter

    public void bind(View view) {
        this.view = view;
    }

    // code logic
    // note: This will get invoked directly by the view
    // note: all possible state changing methods are public
    // with the gwt way the presenter is black box as view is concerned

    public void fahrenheitValueChanged(String value) {
        try {
            int fahrenheitValue = Integer.parseInt(value);
            int celsiusValue = (int) Math.round(((fahrenheitValue - 32.0) / 9.0) * 5.0);
            view.setCelsiusText(String.valueOf(celsiusValue));
            this.fahrenheit = String.valueOf(fahrenheitValue);
            this.celsius = String.valueOf(celsiusValue);
            history.newItem(HistoryExample.CONVERT,"c",celsius,"f",fahrenheit);
        } catch (NumberFormatException e) {
            // can't really do anything
            view.setCelsiusText("N/A");
        }

    }

    public void celsiusValueChanged(String value) {
        try {
            int celsiusValue = Integer.parseInt(value);
            int fahrenheitValue = (int) Math.round(((celsiusValue * 9.0) / 5.0) + 32.0);
            view.setFahrenheitText("" + fahrenheitValue);
            this.fahrenheit = String.valueOf(fahrenheitValue);
            this.celsius = String.valueOf(celsiusValue);
            history.newItem(HistoryExample.CONVERT,"c",celsius,"f",fahrenheit);
        } catch (NumberFormatException e) {
            // can't really do anything
            view.setFahrenheitText("N/A");
        }
    }

    public void onHistoryEvent(HistoryItem item) {
        String celsius = item.getParameters().get("c");
        // if this changes the values then update the ui
        if (!this.celsius.equals(celsius)) {
            this.celsius = celsius;
            this.fahrenheit = item.getParameters().get("f");
            view.setCelsiusText(celsius);
            view.setFahrenheitText(fahrenheit);
        }
    }
}
