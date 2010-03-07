package guicymorphic.webapp.contacts.server.bl;

import com.google.inject.Inject;
import guicymorphic.webapp.contacts.shared.dto.ContactDto;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 7, 2010
 * Time: 8:24:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class PopulatorBl {


    private static final String[] contactsFirstNameData = new String[]{
            "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
            "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
            "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush",
            "Abigail", "Chad", "Terry", "Bell"};

    private final String[] contactsLastNameData = new String[]{
            "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
            "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
            "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard",
            "Louis", "Andrews", "English", "Snedden"};

    private final String[] contactsEmailData = new String[]{
            "mark@example.com", "hollie@example.com", "boticario@example.com",
            "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com",
            "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
            "qetlyxxogg@example.com", "antenas_sul@example.com",
            "cblake@example.com", "gailh@example.com", "orville@example.com",
            "post_master@example.com", "rchilders@example.com", "buster@example.com",
            "user31065@example.com", "ftsgeolbx@example.com"};
    private final ContactsBl bl;


    @Inject
    public PopulatorBl(ContactsBl bl) {
        this.bl = bl;
    }

    public void populate(int size) {

        for (int i = 0; i < size; ++i) {
            ContactDto contact = new ContactDto(null, contactsFirstNameData[((int) (Math.random() * contactsFirstNameData.length))], contactsLastNameData[((int) (Math.random() * contactsLastNameData.length))], contactsEmailData[((int) (Math.random() * contactsEmailData.length))]);
            bl.addOrUpdateContact(contact);
        }
    }

}
