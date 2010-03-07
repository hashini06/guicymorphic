package guicymorphic.webapp.contacts.client.event;

import com.google.gwt.event.shared.GwtEvent;
import guicymorphic.webapp.contacts.shared.dto.ContactDto;

public class ContactUpdatedEvent extends GwtEvent<ContactUpdatedEventHandler> {
    public static Type<ContactUpdatedEventHandler> TYPE = new Type<ContactUpdatedEventHandler>();
    private final ContactDto updatedContact;

    public ContactUpdatedEvent(ContactDto updatedContact) {
        this.updatedContact = updatedContact;
    }

    public ContactDto getUpdatedContact() {
        return updatedContact;
    }


    @Override
    public Type<ContactUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ContactUpdatedEventHandler handler) {
        handler.onContactUpdated(this);
    }
}
