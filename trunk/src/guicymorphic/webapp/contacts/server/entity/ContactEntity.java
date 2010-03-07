package guicymorphic.webapp.contacts.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA. User: alen Date: Feb 27, 2010 Time: 8:58:35 PM To change this template use File | Settings
 * | File Templates.
 */
@Entity
public class ContactEntity {

    @Id
    @GeneratedValue
    public Long id;

    public String firstName;
    public String lastName;
    public String emailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFormattedName() {
        return firstName + " " + lastName;
    }
}

