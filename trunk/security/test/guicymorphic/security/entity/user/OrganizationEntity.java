package guicymorphic.security.entity.user;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 21:56:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class OrganizationEntity {

    @Id
    private Long id;
    private String username;

    public OrganizationEntity() {
    }

    public OrganizationEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
