package guicymorphic.security.hibernate;

import com.google.inject.Inject;
import com.google.inject.Injector;
import guicymorphic.fw.dal.hibernate.TransactionBubble;
import guicymorphic.fw.dal.hibernate.TransactionBubbles;
import guicymorphic.security.AclDefaultServiceImpl;
import guicymorphic.security.entity.acl.AclEntryEntity;
import guicymorphic.security.entity.acl.AclTypeLinkEntity;
import guicymorphic.security.entity.equipment.AbstractEquipmentEntity;
import guicymorphic.security.entity.equipment.EquipmentGroupEntity;
import guicymorphic.security.entity.equipment.EquipmentInstanceEntity;
import guicymorphic.security.entity.user.GroupEntity;
import guicymorphic.security.entity.user.OrganizationEntity;
import guicymorphic.security.entity.user.UserEntity;
import guicymorphic.security.model.EquipmentPermission;
import junit.framework.TestCase;
import org.hibernate.Session;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 22:03:05
 * To change this template use File | Settings | File Templates.
 */
public class AclHibernateServiceImpl extends TestCase {


    @Inject
    public TransactionBubbles bubbles;

    @Inject
    public AclDefaultServiceImpl acl;

    @Override
    protected void setUp() throws Exception {
        bubbles.execute(new TransactionBubble<Void>() {
            public Void doInTransaction(Session session, Injector injector) {
                // Add Acl Type Links
                session.saveOrUpdate(new AclTypeLinkEntity(1L, UserEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(2L, GroupEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(3L, OrganizationEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(4L, AbstractEquipmentEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(5L, EquipmentPermission.class.getName()));

                // Add Equipment
                EquipmentGroupEntity root = new EquipmentGroupEntity(1L, null, "Root Group");
                session.saveOrUpdate(root);
                EquipmentGroupEntity primary = new EquipmentGroupEntity(2L, root, "Primary Group");
                session.saveOrUpdate(primary);
                session.saveOrUpdate(new EquipmentInstanceEntity(3L, primary, "Device Instance V1"));

                // Add Groups
                GroupEntity operators = new GroupEntity(1L, null, "Operators");
                session.saveOrUpdate(operators);
                session.saveOrUpdate(new GroupEntity(2L, operators, "Overwatch"));

                // Add Organizations
                OrganizationEntity freedomProgress = new OrganizationEntity(1L, "Freedom Progress");
                session.saveOrUpdate(freedomProgress);

                //  Add Acl Entriesh
                //  User has ADMIN right on  Device Instance V1
                session.saveOrUpdate(new AclEntryEntity(1L, new AclTypeLinkEntity(1L), "1", new AclTypeLinkEntity(5L), EquipmentPermission.ADMIN.name(), new AclTypeLinkEntity(4L), "3", new Date(), null));
                // Overwatch Group has KILL right on  Device Instance V1
                session.saveOrUpdate(new AclEntryEntity(2L, new AclTypeLinkEntity(2L), "2", new AclTypeLinkEntity(5L), EquipmentPermission.KILL.name(), new AclTypeLinkEntity(4L), "3", new Date(), null));
                // Operators Group has RESET right on devices in Primary Group
                session.saveOrUpdate(new AclEntryEntity(3L, new AclTypeLinkEntity(2L), "1", new AclTypeLinkEntity(5L), EquipmentPermission.RESET.name(), new AclTypeLinkEntity(4L), "2", new Date(), null));
                // Freedom Progress Organization has VIEW right on devices in group Root Group
                session.saveOrUpdate(new AclEntryEntity(4L, new AclTypeLinkEntity(3L), "1", new AclTypeLinkEntity(5L), EquipmentPermission.VIEW.name(), new AclTypeLinkEntity(4L), "1", new Date(), null));

                // Add user in group and organization

                UserEntity defaultUser = new UserEntity(1L, "jkranjski");
                defaultUser.getGroups().add(operators);
                defaultUser.getOrganizations().add(freedomProgress);
                session.saveOrUpdate(defaultUser);
                return null;
            }
        });


    }

    /**
     * Test for right granted on Object for the User.
     */
    public void testUserOnObjectPermission() {
        assertTrue(acl.hasPermission(UserEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.ADMIN.name()));

    }

    /**
     * Group has permission directly on the queried Object
     */
    public void testGroupOnObjectPermission() {
        // user has permission as is part of the group
        assertTrue(acl.hasPermission(UserEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.KILL.name()));
        // the group has permission
        assertTrue(acl.hasPermission(GroupEntity.class.getName(), "2", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.KILL.name()));
    }

    /**
     * Group has permission on a parent of the queried Object
     */
    public void testGroupOnObjectByParentPermission() {
        // user has permission as the parent object is part of a group that has the permission and the user is part of that group
        assertTrue(acl.hasPermission(UserEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.RESET.name()));
        // the group has permission
        assertTrue(acl.hasPermission(GroupEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "2", EquipmentPermission.class.getName(), EquipmentPermission.RESET.name()));
        assertTrue(acl.hasPermission(GroupEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.RESET.name()));

    }

    /**
     * Organization has permission on a parent of the parent of the queried Object
     */
    public void testOrganizationOnObjectByParentPermission() {
        // user has permission as users organization has permission on the root equipment group
        assertTrue(acl.hasPermission(UserEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.VIEW.name()));
        // the org has permission on the root equipment group
        assertTrue(acl.hasPermission(OrganizationEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "1", EquipmentPermission.class.getName(), EquipmentPermission.VIEW.name()));
        assertTrue(acl.hasPermission(OrganizationEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "2", EquipmentPermission.class.getName(), EquipmentPermission.VIEW.name()));
        assertTrue(acl.hasPermission(OrganizationEntity.class.getName(), "1", AbstractEquipmentEntity.class.getName(), "3", EquipmentPermission.class.getName(), EquipmentPermission.VIEW.name()));

    }

}
