package guicymorphic.contacts.client.cfg;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import guicymorphic.contacts.client.mvp.editor.EditorPresenter;
import guicymorphic.contacts.client.mvp.editor.EditorView;
import guicymorphic.contacts.client.mvp.overview.ContactsOverviewPresenter;
import guicymorphic.contacts.client.mvp.overview.ContactsOverviewView;
import guicymorphic.fw.gwt.common.client.BrowserHistory;
import guicymorphic.fw.gwt.common.client.BrowserHistoryImpl;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 7:52:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(ContactsOverviewPresenter.View.class).to(ContactsOverviewView.class);
        bind(EditorPresenter.View.class).to(EditorView.class);
        bind(BrowserHistory.class).to(BrowserHistoryImpl.class).in(Singleton.class);
    }
}
