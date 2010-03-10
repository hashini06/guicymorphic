package guicymorphic.tutorial.mvp.dotNet.simple;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Test for {@link guicymorphic.tutorial.mvp.dotNet.simple.DotNetTempConverterPresenter}.
 *
 * @author Alen Vrecko
 */
public class DotNetTempConverterPresenterTest {


    @Mock
    DotNetTempConverterPresenter.View view;
    DotNetTempConverterPresenter presenter;

    public DotNetTempConverterPresenterTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init() {
        // cannot initialize the presenter in the field as view is null until initMocks is called
        // maybe will make it so that @Mock can interact with other fields but that requires a lot of heavy lifting
        presenter = new DotNetTempConverterPresenter();

        // the view will installs itself on the presenter
        presenter.bind(view);
    }

    @Test
    public void celsiusToFahrenheitTest() {

        // lets change the value of the celsius value by directly calling the presenter
        presenter.celsiusValueChanged("23");

        // expecting change of fahrenheit value
        verify(view).setFahrenheitText("73");

    }

    @Test
    public void fahrenheitToCelsiusTest() {

        // lets change the value of the celsius value
        presenter.fahrenheitValueChanged("73");

        // expecting change of fahrenheit value
        verify(view).setCelsiusText("23");

    }
}
