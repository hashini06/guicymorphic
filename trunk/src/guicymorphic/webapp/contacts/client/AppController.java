package guicymorphic.webapp.contacts.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.google.inject.Provider;
import guicymorphic.webapp.contacts.client.event.*;
import guicymorphic.webapp.contacts.client.presenter.ContactsPresenter;
import guicymorphic.webapp.contacts.client.presenter.EditContactPresenter;
import guicymorphic.webapp.contacts.client.presenter.Presenter;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final EventBus eventBus;
    private final Provider<ContactsPresenter> contactsPresenters;
    private final Provider<EditContactPresenter> editPresenters;
    private HasWidgets container;

    @Inject
    public AppController(EventBus eventBus,
                         Provider<ContactsPresenter> contactsPresenters,
                         Provider<EditContactPresenter> editPresenters) {
        this.eventBus = eventBus;
        this.contactsPresenters = contactsPresenters;
        this.editPresenters = editPresenters;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddContactEvent.TYPE,
                new AddContactEventHandler() {
                    public void onAddContact(AddContactEvent event) {
                        doAddNewContact();
                    }
                });

        eventBus.addHandler(EditContactEvent.TYPE,
                new EditContactEventHandler() {
                    public void onEditContact(EditContactEvent event) {
                        doEditContact(event.getId());
                    }
                });

        eventBus.addHandler(EditContactCancelledEvent.TYPE,
                new EditContactCancelledEventHandler() {
                    public void onEditContactCancelled(EditContactCancelledEvent event) {
                        doEditContactCancelled();
                    }
                });

        eventBus.addHandler(ContactUpdatedEvent.TYPE,
                new ContactUpdatedEventHandler() {
                    public void onContactUpdated(ContactUpdatedEvent event) {
                        doContactUpdated();
                    }
                });
    }

    private void doAddNewContact() {
        History.newItem("add");
    }

    private void doEditContact(String id) {
        History.newItem("edit@id=" + id, false);
        EditContactPresenter presenter = editPresenters.get();
        presenter.edit(id);
        presenter.go(container);
    }

    private void doEditContactCancelled() {
        History.newItem("list");
    }

    private void doContactUpdated() {
        History.newItem("list");
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("list");
        } else {
            History.fireCurrentHistoryState();
        }
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if (token.equals("list")) {
                presenter = contactsPresenters.get();
            } else if (token.equals("add")) {
                presenter = editPresenters.get();
            } else if (token.equals("edit")) {
                presenter = editPresenters.get();
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
