package guicymorphic.examples.gwt.mvp.dotNet;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;


/**
 * View implementation for the dot net style presenter.
 *
 * @author Alen Vrecko
 */
public class DotNetTempConverterView extends Composite implements DotNetTempConverterPresenter.View {
    private TextBox celsiusBox = new TextBox();
    private TextBox fahrenheitBox = new TextBox();
    private final DotNetTempConverterPresenter presenter;

    // 1. get the dependencies

    public DotNetTempConverterView(DotNetTempConverterPresenter presenter) {
        this.presenter = presenter;

        Grid grid = new Grid(2, 2);
        grid.setWidget(0, 0, new Label("Celsius"));
        grid.setWidget(0, 1, celsiusBox);

        grid.setWidget(1, 0, new Label("Fahrenheit"));
        grid.setWidget(1, 1, fahrenheitBox);

        bind();
        initWidget(grid);
        // unsafe publishing method
        // presenter.bind(this);
        // _this_ should not be published from the constructor use onLoad instead
    }

    private void bind() {
        // in gwt style MVP this is in the presenter here we deal with events directly in the view
        celsiusBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                presenter.celsiusValueChanged(event.getValue());
            }
        });

        fahrenheitBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                presenter.fahrenheitValueChanged(event.getValue());
            }
        });
    }

    @Override
    protected void onLoad() {
        presenter.bind(this);
    }

    public void setCelsiusText(String value) {
        celsiusBox.setText(value);
    }

    public void setFahrenheitText(String value) {
        fahrenheitBox.setText(value);
    }
}
