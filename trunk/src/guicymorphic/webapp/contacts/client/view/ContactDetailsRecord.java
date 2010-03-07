package guicymorphic.webapp.contacts.client.view;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import guicymorphic.webapp.contacts.shared.dto.ContactDisplayDto;


public class ContactDetailsRecord extends ListGridRecord {
    private static final String CONTACT_ID = "cid";
    private static final String DISPLAY_NAME = "displayName";


    public ContactDetailsRecord(ContactDisplayDto details) {
        setAttribute(DISPLAY_NAME, details.getDisplayName());
        setAttribute(CONTACT_ID, details.getId());
    }

    public String getId() {
        return getAttribute(CONTACT_ID);
    }

    public String getDisplayName() {
        return getAttribute(CONTACT_ID);
    }
}
