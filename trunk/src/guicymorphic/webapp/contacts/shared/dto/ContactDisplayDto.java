package guicymorphic.webapp.contacts.shared.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ContactDisplayDto implements Serializable {
    private String id;
    private String displayName;

    public ContactDisplayDto() {
    }

    public ContactDisplayDto(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
