package guicymorphic.tutorial.mvp.client.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 10, 2010
 * Time: 12:25:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonDto implements Serializable {

    private String firstname;
    private String latname;

    // concrete type to make gwt serialization easier
    private ArrayList<FamilyMemberDto> familiyMembers = new ArrayList<FamilyMemberDto>();

    public PersonDto() {

    }

    public PersonDto(String firstname, String latname, ArrayList<FamilyMemberDto> familiyMembers) {
        this.firstname = firstname;
        this.latname = latname;
        this.familiyMembers = familiyMembers;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLatname() {
        return latname;
    }

    public void setLatname(String latname) {
        this.latname = latname;
    }

    public ArrayList<FamilyMemberDto> getFamiliyMembers() {
        return familiyMembers;
    }

    public void setFamiliyMembers(ArrayList<FamilyMemberDto> familiyMembers) {
        this.familiyMembers = familiyMembers;
    }
}
