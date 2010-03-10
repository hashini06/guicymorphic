package guicymorphic.tutorial.mvp.gwt.simple;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static guicymorphic.fw.util.Reflections.unicast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link guicymorphic.tutorial.mvp.gwt.simple.GwtTempConverterPresenter}.
 *
 * @author Alen Vrecko
 */
public class GwtTempConverterPresenterTest {


    @Mock
    GwtTempConverterPresenter.View view;

    @Mock
    HasValueChangeHandlers<String> celsiusHandlers;

    @Mock
    HasValueChangeHandlers<String> fahrenheitHandlers;

    ArgumentCaptor<ValueChangeHandler<String>> celsiusHandlerCaptor = unicast(ArgumentCaptor.forClass(ValueChangeHandler.class));

    ArgumentCaptor<ValueChangeHandler<String>> fahrenheitHandlerCaptor = unicast(ArgumentCaptor.forClass(ValueChangeHandler.class));


    GwtTempConverterPresenter presenter;

    public GwtTempConverterPresenterTest() {
        // put this is common base class or similar
        MockitoAnnotations.initMocks(this);


    }

    @Before
    public void init() {
        when(view.celsiusText()).thenReturn(celsiusHandlers);
        when(view.fahrenheitText()).thenReturn(fahrenheitHandlers);

        // cannot initialize the presenter in the field as view is null until initMocks is called
        // maybe will make it so that @Mock can interact with other fields but that requires a lot of heavy lifting
        presenter = new GwtTempConverterPresenter(view);

        // expecting that the presenter registered itself on the view
        // capture the installed listeners (handlers) as we will invoke the events on them
        verify(celsiusHandlers).addValueChangeHandler(celsiusHandlerCaptor.capture());
        verify(fahrenheitHandlers).addValueChangeHandler(fahrenheitHandlerCaptor.capture());
    }

    @Test
    public void celsiusToFahrenheitTest() {

        // lets change the value of the celsius value
        // note: we fire the event on the handler that the presenter installed
        celsiusHandlerCaptor.getValue().onValueChange(new ValueChangeEvent<String>("23") {
        });

        // expecting change of fahrenheit value
        verify(view).setFahrenheitText("73");

    }

    @Test
    public void fahrenheitToCelsiusTest() {

        // lets change the value of the celsius value
        fahrenheitHandlerCaptor.getValue().onValueChange(new ValueChangeEvent<String>("73") {
        });

        // expecting change of fahrenheit value
        verify(view).setCelsiusText("23");

    }

}
