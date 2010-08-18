package guicymorphic.security.entity.equipment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 21:58:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("EQ_GROUP")
public class EquipmentGroupEntity extends AbstractEquipmentEntity {
    public EquipmentGroupEntity() {
    }

    public EquipmentGroupEntity(Long id, AbstractEquipmentEntity parent, String name) {
        super(id, parent, name);
    }
}
