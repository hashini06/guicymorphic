package guicymorphic.security.entity.equipment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 21:58:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("EQ_INSTANCE")
public class EquipmentInstanceEntity extends AbstractEquipmentEntity {
    public EquipmentInstanceEntity() {
    }

    public EquipmentInstanceEntity(Long id, AbstractEquipmentEntity parent, String name) {
        super(id, parent, name);
    }
}
