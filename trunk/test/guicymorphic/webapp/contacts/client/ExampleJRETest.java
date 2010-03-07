package guicymorphic.webapp.contacts.client;

import guicymorphic.webapp.contacts.client.event.EventBus;
import guicymorphic.webapp.contacts.client.presenter.ContactsPresenter;
import guicymorphic.webapp.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.webapp.contacts.shared.rpc.ContactsServiceAsync;
import junit.framework.TestCase;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;


public class ExampleJRETest extends TestCase {
    private ContactsPresenter contactsPresenter;
    private ContactsServiceAsync mockRpcService;
    private EventBus eventBus;
    private ContactsPresenter.Display mockDisplay;

    protected void setUp() {
        mockRpcService = mock(ContactsServiceAsync.class);
        eventBus = new EventBus();
        mockDisplay = mock(ContactsPresenter.Display.class);
        contactsPresenter = new ContactsPresenter(mockRpcService, eventBus, mockDisplay);
    }

    public void testContactSort() {
        ArrayList<ContactDisplayDto> contactDetails = new ArrayList<ContactDisplayDto>();
        contactDetails.add(new ContactDisplayDto("0", "c_contact"));
        contactDetails.add(new ContactDisplayDto("1", "b_contact"));
        contactDetails.add(new ContactDisplayDto("2", "a_contact"));
        contactsPresenter.setContactDetails(contactDetails);
        contactsPresenter.sortContactDetails();
        assertTrue(contactsPresenter.getContactDetail(0).getDisplayName().equals("a_contact"));
        assertTrue(contactsPresenter.getContactDetail(1).getDisplayName().equals("b_contact"));
        assertTrue(contactsPresenter.getContactDetail(2).getDisplayName().equals("c_contact"));
    }
}