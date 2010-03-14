package guicymorphic.examples.gwt.mvp.google;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.ui.*;

/**
 * Gwt style implementation of view.
 *
 * @author Alen Vrecko
 */
public class GwtTempConverterView extends Composite implements GwtTempConverterPresenter.View {


    private TextBox celsiusBox = new TextBox();
    private TextBox fahrenheitBox = new TextBox();

    // we shouldn't really have any dependencies

    public GwtTempConverterView() {
        Grid grid = new Grid(2, 2);
        grid.setWidget(0, 0, new Label("Celsius"));
        grid.setWidget(0, 1, celsiusBox);

        grid.setWidget(1, 0, new Label("Fahrenheit"));
        grid.setWidget(1, 1, fahrenheitBox);

        initWidget(grid);
    }

    public void setCelsiusText(String value) {
        celsiusBox.setText(value);
    }

    public void setFahrenheitText(String value) {
        fahrenheitBox.setText(value);
    }

    public HasValueChangeHandlers<String> celsiusText() {
        return celsiusBox;
    }

    public HasValueChangeHandlers<String> fahrenheitText() {
        return fahrenheitBox;
    }

    public Widget asWidget() {
        return this;
    }


}
