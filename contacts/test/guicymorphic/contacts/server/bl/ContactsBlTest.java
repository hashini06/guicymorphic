package guicymorphic.contacts.server.bl;

import com.google.inject.*;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import guicymorphic.contacts.server.cfg.ContactsTestBoot;
import guicymorphic.contacts.server.dal.ContactDao;
import guicymorphic.contacts.server.entity.ContactEntity;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;
import guicymorphic.contacts.shared.dto.ContactDto;
import guicymorphic.contacts.shared.rpc.ContactsService;
import guicymorphic.fw.dal.hibernate.ExtendedAnnotationConfiguration;
import guicymorphic.fw.dal.hibernate.TransactionWrapper;
import guicymorphic.fw.dal.hibernate.TransactionalCommand;
import guicymorphic.fw.service.GuiceRemoteServiceServlet;
import guicymorphic.fw.util.Properties2;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 28, 2010 Time: 12:11:11 PM To change this template use File | Settings
 * | File Templates.
 */
public class ContactsBlTest {
   
    private static Injector injector;

    @Inject
    ContactsBl contactsBl;
    private static final String TEST_FIRSTNAME = "Janez";
    private static final String TEST_LASTNAME = "Kranjski";
    private static final String TEST_EMAIL = "j@kranjski.com";
    private static final int POPULATION_SIZE = 20;

    @BeforeClass
    public static void pre() {
        injector = Guice.createInjector(new ContactsTestBoot());
        injector.getInstance(PersistenceService.class).start();
    }

    @AfterClass
    public static void post() {
        injector.getInstance(SessionFactory.class).close();
    }


    @Before
    public void init() {
        injector.injectMembers(this);

        // as methods in DAO are not transactional we need to wrap this in transaction wrapper to be in a transaction
        // same semantics apply as for transactional methods
        injector.getInstance(TransactionWrapper.class).execute(new TransactionalCommand() {
            public void doInTransaction(Session session, Injector injector) {
                injector.getInstance(ContactDao.class).deleteAll();
            }
        });
    }

    @Test
    public void testAddContact() throws Exception {
        ContactDto savedDto = contactsBl.addOrUpdateContact(new ContactDto(null, TEST_FIRSTNAME, TEST_LASTNAME, TEST_EMAIL));

        assertNotNull(savedDto.getId());
        assertEquals(TEST_FIRSTNAME, savedDto.getFirstName());
        assertEquals(TEST_LASTNAME, savedDto.getLastName());
        assertEquals(TEST_EMAIL, savedDto.getEmailAddress());

        ContactDto foundDto = contactsBl.getContact(savedDto.getId());

        assertNotSame(savedDto, foundDto);

        assertEquals(savedDto.getId(), foundDto.getId());
        assertEquals(savedDto.getFirstName(), foundDto.getFirstName());
        assertEquals(savedDto.getLastName(), foundDto.getLastName());
        assertEquals(savedDto.getEmailAddress(), foundDto.getEmailAddress());
    }

    @Test
    public void testDeleteContact() throws Exception {
        String savedDto1 = contactsBl.addOrUpdateContact(new ContactDto(null, TEST_FIRSTNAME + "_1", TEST_LASTNAME + "_1", TEST_EMAIL + "_1")).getId();
        String savedDto2 = contactsBl.addOrUpdateContact(new ContactDto(null, TEST_FIRSTNAME + "_2", TEST_LASTNAME + "_2", TEST_EMAIL + "_2")).getId();


        Boolean success = contactsBl.deleteContact(savedDto1);
        assertTrue(success);

        ContactDto retrievedDto1 = contactsBl.getContact(savedDto1);
        assertNull(retrievedDto1);

        ContactDto retrievedDto2 = contactsBl.getContact(savedDto2);
        Assert.assertNotNull(retrievedDto2);
        assertEquals(TEST_FIRSTNAME + "_2", retrievedDto2.getFirstName());
    }

    @Test
    public void testDeleteContacts() throws Exception {
        ArrayList<ContactDto> contactDtos = new ArrayList<ContactDto>();

        for (int i = 0; i < 20; i++) {
            contactDtos.add(contactsBl.addOrUpdateContact(
                    new ContactDto(null, TEST_FIRSTNAME + "_" + i, TEST_LASTNAME + "_" + i, TEST_EMAIL + "_" + i)));
        }

        // manual selection (:

        ArrayList<String> idsToDelete = new ArrayList<String>();
        idsToDelete.add(contactDtos.get(3).getId());
        idsToDelete.add(contactDtos.get(5).getId());
        idsToDelete.add(contactDtos.get(7).getId());
        idsToDelete.add(contactDtos.get(12).getId());

        ArrayList<ContactDisplayDto> selectionWithoutDeleted = contactsBl.deleteContacts(idsToDelete);

        for (ContactDisplayDto contactDisplayDto : selectionWithoutDeleted) {
            String name = contactDisplayDto.getDisplayName();
            if (name.endsWith("_3") || name.endsWith("_5") || name.endsWith("_7") || name.endsWith("_12")) {
                fail();
            }
        }

        assertEquals(16, selectionWithoutDeleted.size());

    }

    @Test
    public void testUpdateContact() throws Exception {

        ContactDto dto = contactsBl.addOrUpdateContact(new ContactDto(null, TEST_FIRSTNAME, TEST_FIRSTNAME, TEST_EMAIL));

        String updatedFirstname = dto.getFirstName() + "_UPDATED";
        dto.setFirstName(updatedFirstname);
        String updatedLastname = dto.getLastName() + "_UPDATED";
        dto.setLastName(updatedLastname);
        String updatedEmail = dto.getEmailAddress() + "_UPDATED";
        dto.setEmailAddress(updatedEmail);

        ContactDto updated = contactsBl.addOrUpdateContact(dto);

        assertEquals(updatedFirstname, updated.getFirstName());
        assertEquals(updatedLastname, updated.getLastName());
        assertEquals(updatedEmail, updated.getEmailAddress());

        ContactDto retrieved = contactsBl.getContact(dto.getId());

        assertEquals(updatedFirstname, retrieved.getFirstName());
        assertEquals(updatedLastname, retrieved.getLastName());
        assertEquals(updatedEmail, retrieved.getEmailAddress());
    }
}
