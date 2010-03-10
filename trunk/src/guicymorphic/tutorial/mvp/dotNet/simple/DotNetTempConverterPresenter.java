package guicymorphic.tutorial.mvp.dotNet.simple;

/**
 * Dot net style presenter example.
 *
 * @author Alen Vrecko
 */
public class DotNetTempConverterPresenter {

    // 1. What do we need from the view

    public interface View {

        // note: we only specify what we will do on the view
        // we don't need any hooks from the view as the view will call
        // methods on the presenter _directly_

        void setCelsiusText(String value);

        void setFahrenheitText(String value);
    }

    // 2. Here we would get dependencies such as EventBus and Services but NOT the view

    public DotNetTempConverterPresenter() {
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
        } catch (NumberFormatException e) {
            // can't really do anything
            view.setFahrenheitText("N/A");
        }
    }
}
