package guicymorphic.tutorial.mvp.client.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 10, 2010
 * Time: 12:26:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class FamilyMemberDto implements Serializable {

    private String firstname;
    private String lastname;

    public FamilyMemberDto() {
    }

    public FamilyMemberDto(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
