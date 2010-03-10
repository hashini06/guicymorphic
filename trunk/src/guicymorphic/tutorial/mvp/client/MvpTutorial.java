package guicymorphic.tutorial.mvp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.tutorial.mvp.dotNet.simple.DotNetTempConverterPresenter;
import guicymorphic.tutorial.mvp.dotNet.simple.DotNetTempConverterView;
import guicymorphic.tutorial.mvp.gwt.simple.GwtTempConverterPresenter;
import guicymorphic.tutorial.mvp.gwt.simple.GwtTempConverterView;

/**
 * Entry point for the gwt tutorial.
 *
 * @author Alen Vrecko
 */
public class MvpTutorial implements EntryPoint {


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
