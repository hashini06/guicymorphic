package guicymorphic.webapp.contacts.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import guicymorphic.fw.gwt.data.HasValueClickHandlers;
import guicymorphic.fw.gwt.data.ValueClickHandler;
import guicymorphic.webapp.contacts.client.event.AddContactEvent;
import guicymorphic.webapp.contacts.client.event.EditContactEvent;
import guicymorphic.webapp.contacts.client.event.EventBus;
import guicymorphic.webapp.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.webapp.contacts.shared.rpc.ContactsServiceAsync;

import java.util.ArrayList;
import java.util.List;

public class ContactsPresenter implements Presenter {

    private List<ContactDisplayDto> contactDetails;

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getDeleteButton();

        HasValueClickHandlers<String> getList();

        void setData(ArrayList<ContactDisplayDto> data);

        ArrayList<String> getSelectedRows();

        Widget asWidget();
    }

    private final ContactsServiceAsync rpcService;
    private final EventBus eventBus;
    private final Display display;

    @Inject
    public ContactsPresenter(ContactsServiceAsync rpcService, EventBus eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {
        display.getAddButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new AddContactEvent());
            }
        });

        display.getDeleteButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteSelectedContacts();
            }
        });


        display.getList().addValueClickHandler(new ValueClickHandler<String>() {
            @Override
            public void onValueClick(String value) {
                eventBus.fireEvent(new EditContactEvent(value));
            }
        });

    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchContactDetails();
    }

    public void sortContactDetails() {

        // Yes, we could use a more optimized method of sorting, but the
        //  point is to create a test case that helps illustrate the higher
        //  level concepts used when creating MVP-based applications.
        //
        for (int i = 0; i < contactDetails.size(); ++i) {
            for (int j = 0; j < contactDetails.size() - 1; ++j) {
                if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(contactDetails.get(j + 1).getDisplayName()) >= 0) {
                    ContactDisplayDto tmp = contactDetails.get(j);
                    contactDetails.set(j, contactDetails.get(j + 1));
                    contactDetails.set(j + 1, tmp);
                }
            }
        }
    }

    public void setContactDetails(List<ContactDisplayDto> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public ContactDisplayDto getContactDetail(int index) {
        return contactDetails.get(index);
    }

    private void fetchContactDetails() {
        rpcService.getContactDetails(new AsyncCallback<ArrayList<ContactDisplayDto>>() {
            public void onSuccess(ArrayList<ContactDisplayDto> result) {
                display.setData(result);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });
    }

    private void deleteSelectedContacts() {
        ArrayList<String> selectedRows = display.getSelectedRows();

        rpcService.deleteContacts(selectedRows, new AsyncCallback<ArrayList<ContactDisplayDto>>() {
            public void onSuccess(ArrayList<ContactDisplayDto> result) {
                display.setData(result);

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error deleting selected contacts");
            }
        });
    }
}
