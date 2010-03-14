package guicymorphic.examples.gwt.mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.examples.gwt.mvp.dotNet.DotNetTempConverterPresenter;
import guicymorphic.examples.gwt.mvp.dotNet.DotNetTempConverterView;
import guicymorphic.examples.gwt.mvp.google.GwtTempConverterPresenter;
import guicymorphic.examples.gwt.mvp.google.GwtTempConverterView;

/**
 * Entry point for the gwt tutorial.
 *
 * @author Alen Vrecko
 */
public class MvpExample implements EntryPoint {


    public void onModuleLoad() {
        // placeholder for gwt style presenter
        Panel gwtPlaceholder = RootPanel.get("gwtMvp");

        // placeholder for gwt style presenter
        Panel dotNetPlaceholder = RootPanel.get("dotNetMvp");

        // Gwt style MVP
        GwtTempConverterPresenter presenter = new GwtTempConverterPresenter(new GwtTempConverterView());
        // we ask the presenter to get in the container
        presenter.go(gwtPlaceholder);

        // dotNet style MVP
        DotNetTempConverterView view = new DotNetTempConverterView(new DotNetTempConverterPresenter());
        // put the view directly in the container
        dotNetPlaceholder.add(view);


    }


}
