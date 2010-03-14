package guicymorphic.contacts.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Provider;
import guicymorphic.contacts.client.mvp.editor.EditorPresenter;
import guicymorphic.contacts.client.mvp.overview.ContactsOverviewPresenter;
import guicymorphic.fw.gwt.common.client.BrowserHistory;
import guicymorphic.fw.gwt.common.client.EventBus;
import guicymorphic.fw.gwt.common.client.HistoryItem;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 5:02:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppController implements ValueChangeHandler<String> {

    public static final String OVERVIEW = "overview";
    public static final String EDITOR = "editor";

    private final EventBus eventBus;
    private final Provider<ContactsOverviewPresenter.View> overviews;
    private final Provider<EditorPresenter.View> editors;
    private final BrowserHistory history;
    private HasWidgets panel;


    @Inject
    public AppController(EventBus eventBus, Provider<ContactsOverviewPresenter.View> overviews,Provider<EditorPresenter.View> editors, BrowserHistory history) {
        this.eventBus = eventBus;
        this.overviews = overviews;
        this.editors = editors;
        this.history = history;
        History.addValueChangeHandler(this);
    }

    public void onValueChange(ValueChangeEvent<String> historyToken) {
        HistoryItem historyItem = HistoryItem.parse(historyToken.getValue());

        if (OVERVIEW.equalsIgnoreCase(historyItem.getId())) {
            // asking the view to get itself in the container
            // maybe going a step to far?
            overviews.get().go(panel);
        } else if (EDITOR.equalsIgnoreCase(historyItem.getId())) {
            EditorPresenter.View view = editors.get();
            // asking the view's presenter to handle the history event
            // it can process the parameters as needed
            view.getPresenter().onHistoryEvent(historyItem);
            view.go(panel);
        }
    }

    public void go(HasWidgets panel) {
         this.panel = panel;
         if ("".equals(History.getToken())) {
            history.newItem(OVERVIEW);
        } else {
            History.fireCurrentHistoryState();
        }
    }
}
