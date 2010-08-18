package guicymorphic.security.entity;

import guicymorphic.security.entity.user.GroupEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 14:03:07
 * To change this template use File | Settings | File Templates.
 */
public class DeviceEntity {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private GroupEntity owningGroup;
}
