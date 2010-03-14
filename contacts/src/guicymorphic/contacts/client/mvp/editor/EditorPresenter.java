package guicymorphic.contacts.client.mvp.editor;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import guicymorphic.contacts.client.AppController;
import guicymorphic.contacts.shared.dto.ContactDto;
import guicymorphic.contacts.shared.rpc.ContactsServiceAsync;
import guicymorphic.fw.gwt.common.client.BrowserHistory;
import guicymorphic.fw.gwt.common.client.HistoryItem;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 4:06:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditorPresenter {
    private View view;
    private final ContactsServiceAsync service;
    private final BrowserHistory history;

    private int rid;

    public interface View {
        void setContact(ContactDto contact);

        void go(HasWidgets panel);

        EditorPresenter getPresenter();
    }

    @Inject
    public EditorPresenter(ContactsServiceAsync service, BrowserHistory history) {
        this.service = service;
        this.history = history;
    }

    public void cancel() {
        history.newItem(AppController.OVERVIEW);
    }

    public void saveOrEdit(ContactDto contactDto) {
        service.addOrUpdateContact(contactDto, new AsyncCallback<ContactDto>() {
            public void onFailure(Throwable caught) {
                // should notify
            }

            public void onSuccess(ContactDto result) {
                history.newItem(AppController.OVERVIEW);
            }
        });
    }

    public void showContact(String cid) {
        service.getContact(cid, new AsyncCallback<ContactDto>() {
            private int trid = ++rid;

            public void onFailure(Throwable caught) {
                if (trid == rid) {
                    // notify properly
                    // cant use UI here like Window alert
                    // should get injected with e.g. Notifications and call on them
                    // or have in view notify(...) or something
                }
            }

            public void onSuccess(ContactDto result) {
                if (trid == rid) {
                    view.setContact(result);
                }
            }
        });

    }

    public void onHistoryEvent(HistoryItem item) {
        String cid = item.getParam("cid");
        if (!"null".equalsIgnoreCase(cid)) {
            showContact(cid);
        }
    }


    public void bind(View view) {
        this.view = view;
    }
}
