package guicymorphic.security.entity.equipment;

import guicymorphic.security.hibernate.AclHibernate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 21:57:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractEquipmentEntity {

    @Id
    private Long id;

    @ManyToOne
    @AclHibernate.AccessControlParent
    private AbstractEquipmentEntity parent;


    protected AbstractEquipmentEntity() {
    }

    protected AbstractEquipmentEntity(Long id, AbstractEquipmentEntity parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractEquipmentEntity getParent() {
        return parent;
    }

    public void setParent(AbstractEquipmentEntity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
