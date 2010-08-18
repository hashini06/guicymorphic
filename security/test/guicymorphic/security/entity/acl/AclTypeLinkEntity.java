package guicymorphic.security.entity.acl;

import guicymorphic.security.hibernate.AclHibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 12:11:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AclTypeLinkEntity {

    @Id
    public Long id;

    @AclHibernate.TypeLinkValue
    public String value;

    // IDE GENERATED BELLOW THIS POINT

    public AclTypeLinkEntity() {
    }

    public AclTypeLinkEntity(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public AclTypeLinkEntity(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
