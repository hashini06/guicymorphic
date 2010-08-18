package guicymorphic.security.entity.user;


import guicymorphic.security.hibernate.AclHibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * A group of users
 */
@Entity
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = -173295251968540076L;

    private Long id;
    private String name;
    private GroupEntity parent;


    /**
     * Default constructor
     */
    public GroupEntity() {
    }

    public GroupEntity(long id, GroupEntity parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    /**
     * Get the unique ID
     */
    @Id
    public Long getId() {
        return id;
    }

    /**
     * Set the unique ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the (short) name of the group
     */
    public String getName() {
        return name;
    }

    /**
     * Set the (short) name of the group
     */
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = GroupEntity.class)
    @AclHibernate.AccessControlParent
    public GroupEntity getParent() {
        return parent;
    }

    public void setParent(GroupEntity parent) {
        this.parent = parent;
    }

}
