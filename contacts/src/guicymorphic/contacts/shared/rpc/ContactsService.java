package guicymorphic.contacts.shared.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.contacts.shared.dto.ContactDto;

import java.util.ArrayList;

@RemoteServiceRelativePath("_rpc")
public interface ContactsService extends RemoteService {

    Boolean deleteContact(String id);

    ArrayList<ContactDisplayDto> deleteContacts(ArrayList<String> ids);

    ArrayList<ContactDisplayDto> getContactDetails();

    ContactDto getContact(String id);

    ContactDto addOrUpdateContact(ContactDto contact);
}
