package guicymorphic.contacts.client.mvp.overview;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;


public class ContactDetailsRecord extends ListGridRecord {
    private static final String CONTACT_ID = "cid";
    private static final String DISPLAY_NAME = "displayName";
    private final ContactDisplayDto details;


    public ContactDetailsRecord(ContactDisplayDto details) {
        this.details = details;
        setAttribute(DISPLAY_NAME, details.getDisplayName());
        setAttribute(CONTACT_ID, details.getId());
    }

    public String getId() {
        return getAttribute(CONTACT_ID);
    }

    public String getDisplayName() {
        return getAttribute(CONTACT_ID);
    }

    public ContactDisplayDto getDto() {
        return details;
    }
}
