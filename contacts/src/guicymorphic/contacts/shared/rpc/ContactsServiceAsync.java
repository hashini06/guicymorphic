package guicymorphic.contacts.shared.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.contacts.shared.dto.ContactDto;

import java.util.ArrayList;

public interface ContactsServiceAsync {

    public void deleteContact(String id, AsyncCallback<Boolean> callback);

    public void deleteContacts(ArrayList<String> ids, AsyncCallback<ArrayList<ContactDisplayDto>> callback);

    public void getContactDetails(AsyncCallback<ArrayList<ContactDisplayDto>> callback);

    public void getContact(String id, AsyncCallback<ContactDto> callback);

    public void addOrUpdateContact(ContactDto contact, AsyncCallback<ContactDto> callback);
}

