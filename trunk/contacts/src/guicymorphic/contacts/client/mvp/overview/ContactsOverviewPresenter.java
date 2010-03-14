package guicymorphic.contacts.client.mvp.overview;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import guicymorphic.contacts.client.AppController;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.contacts.shared.rpc.ContactsServiceAsync;
import guicymorphic.fw.gwt.common.client.BrowserHistory;
import guicymorphic.fw.gwt.common.client.EventBus;
import guicymorphic.fw.gwt.common.client.HistoryItem;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 4:06:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsOverviewPresenter {
    private final ContactsServiceAsync service;
    private final EventBus bus;
    private final BrowserHistory history;
    private View view;

    public interface View {
        void setData(ArrayList<ContactDisplayDto> contacts);
        void setTableText(String message);
        void go(HasWidgets container);
    }


    @Inject
    public ContactsOverviewPresenter(ContactsServiceAsync service, EventBus bus, BrowserHistory history) {
        this.service = service;
        this.bus = bus;
        this.history = history;
    }

    public void bind(View view) {
        this.view = view;
        reloadTable();
    }

    public void editSelected(ContactDisplayDto contact) {
        // bus.fireEvent(new AddOrEditEvent(contact.getId()));
        // we can fire the event directly on the history bus
        history.newItem(AppController.EDITOR,"cid",contact.getId());
    }

    public void addContact() {
        // bus.fireEvent(new AddOrEditEvent(null));
        history.newItem(AppController.EDITOR,"cid",null);
    }

    public void deleteContacts(ArrayList<ContactDisplayDto> contacts) {
        ArrayList<String> ids = new ArrayList<String>();

        for (ContactDisplayDto contact : contacts) {
            ids.add(contact.getId());
        }

        view.setTableText("Deleting contacts...");
        service.deleteContacts(ids, new AsyncCallback<ArrayList<ContactDisplayDto>>() {
            public void onFailure(Throwable caught) {
                view.setTableText("Failed to delete contacts.");
            }

            public void onSuccess(ArrayList<ContactDisplayDto> result) {
                reloadTable();
            }
        });

    }

    private void reloadTable() {
        view.setTableText("Refreshing...");
        service.getContactDetails(new AsyncCallback<ArrayList<ContactDisplayDto>>() {
            public void onFailure(Throwable caught) {
                view.setTableText("Failed to retrieve contacts.");
            }

            public void onSuccess(ArrayList<ContactDisplayDto> result) {
                view.setData(result);
            }
        });
    }


}
