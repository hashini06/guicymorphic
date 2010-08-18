package guicymorphic.security.hibernate;

import com.google.common.collect.ImmutableSetMultimap;
import com.google.inject.Inject;
import com.google.inject.Injector;
import guicymorphic.fw.dal.hibernate.TransactionBubble;
import guicymorphic.fw.dal.hibernate.TransactionBubbles;
import guicymorphic.security.AclElement;
import guicymorphic.security.AclQuery;
import guicymorphic.security.entity.DeviceEntity;
import guicymorphic.security.entity.DeviceGroupEntity;
import guicymorphic.security.entity.acl.AclEntryEntity;
import guicymorphic.security.entity.acl.AclTypeLinkEntity;
import guicymorphic.security.entity.equipment.EquipmentGroupEntity;
import guicymorphic.security.entity.equipment.EquipmentInstanceEntity;
import guicymorphic.security.entity.user.GroupEntity;
import guicymorphic.security.entity.user.OrganizationEntity;
import guicymorphic.security.entity.user.UserEntity;
import guicymorphic.security.model.DevicePermissions;
import guicymorphic.security.model.EquipmentPermission;
import junit.framework.TestCase;
import org.hibernate.Session;

import java.util.Date;

/**
 * Tests if {@link guicymorphic.security.hibernate.AclHibernateRepository} works in order.
 *
 * @author Alen Vrecko
 */
public class AclHibernateRepositoryTest extends TestCase {

    @Inject
    public TransactionBubbles bubbles;

    @Inject
    public AclHibernateRepository repo;

    @Override
    protected void setUp() throws Exception {
        bubbles.execute(new TransactionBubble<Void>() {
            public Void doInTransaction(Session session, Injector injector) {
                session.saveOrUpdate(new AclTypeLinkEntity(1L, UserEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(2L, GroupEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(3L, DevicePermissions.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(4L, DeviceEntity.class.getName()));
                session.saveOrUpdate(new AclTypeLinkEntity(5L, DeviceGroupEntity.class.getName()));


                session.saveOrUpdate(new AclEntryEntity(1L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_OFFLINE.name(), new AclTypeLinkEntity(4L), "11", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(11L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_ONLINE.name(), new AclTypeLinkEntity(5L), "11", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(2L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_OFFLINE.name(), new AclTypeLinkEntity(4L), "12", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(31L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_OFFLINE.name(), new AclTypeLinkEntity(4L), "13", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(32L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_ONLINE.name(), new AclTypeLinkEntity(4L), "13", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(33L, new AclTypeLinkEntity(1L), "10", new AclTypeLinkEntity(3L), DevicePermissions.PUT_ON_STANDBY.name(), new AclTypeLinkEntity(4L), "13", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(21L, new AclTypeLinkEntity(1L), "11", new AclTypeLinkEntity(3L), DevicePermissions.PUT_ONLINE.name(), new AclTypeLinkEntity(4L), "11", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(22L, new AclTypeLinkEntity(1L), "11", new AclTypeLinkEntity(3L), DevicePermissions.PUT_OFFLINE.name(), new AclTypeLinkEntity(4L), "11", new Date(), null));
                session.saveOrUpdate(new AclEntryEntity(23L, new AclTypeLinkEntity(1L), "12", new AclTypeLinkEntity(3L), DevicePermissions.PUT_OFFLINE.name(), new AclTypeLinkEntity(4L), "14", new Date(), null));


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

    public void testForElement() throws Exception {
        // lets ask for user:11 permissions on Object:Device:11
        ImmutableSetMultimap<String, String> answer = repo.queryFor(AclQuery.
                filter(AclElement.OBJECT, DeviceEntity.class.getName(), "11").
                filter(AclElement.PRINCIPAL, UserEntity.class.getName(), "10").
                select(AclElement.PERMISSION));


        ImmutableSetMultimap<String, String> expected = ImmutableSetMultimap.<String, String>builder().put(DevicePermissions.class.getName(), DevicePermissions.PUT_OFFLINE.name()).build();

        assertEquals(expected, answer);


        // lets ask for all users that have any permissions on Object:Device:11
        answer = repo.queryFor(AclQuery.
                filter(AclElement.OBJECT, DeviceEntity.class.getName(), "11").
                select(AclElement.PRINCIPAL));


        assertEquals(ImmutableSetMultimap.<String, String>builder().putAll(UserEntity.class.getName(), "10", "11").build(), answer);

        // lets ask if user:12 has PUT_OFFLINE permission on Device:14

        answer = repo.queryFor(AclQuery.
                filter(AclElement.PERMISSION, DevicePermissions.class.getName(), DevicePermissions.PUT_OFFLINE.name()).
                filter(AclElement.OBJECT, DeviceEntity.class.getName(), "14").
                filter(AclElement.PRINCIPAL, UserEntity.class.getName(), "12").
                select(AclElement.PERMISSION));

        assertEquals(1, answer.size());

        // lets ask if user:12 has PUT_ONLINE permission on Device:14
        answer = repo.queryFor(AclQuery.
                filter(AclElement.PERMISSION, DevicePermissions.class.getName(), DevicePermissions.PUT_ONLINE.name()).
                filter(AclElement.OBJECT, DeviceEntity.class.getName(), "14").
                filter(AclElement.PRINCIPAL, UserEntity.class.getName(), "12").
                select(AclElement.PERMISSION));

        assertEquals(0, answer.size());

    }

    public void testGetParentPrincipals() {
        ImmutableSetMultimap<String, String> answer = repo.findParentPrincipals(UserEntity.class.getName(), "1");
        assertEquals(ImmutableSetMultimap.of(GroupEntity.class.getName(), "1", OrganizationEntity.class.getName(), "1"), answer);
        assertEquals(ImmutableSetMultimap.<String, String>of(), repo.findParentPrincipals(OrganizationEntity.class.getName(), "1"));
        assertEquals(ImmutableSetMultimap.of(GroupEntity.class.getName(), "2"), repo.findParentPrincipals(GroupEntity.class.getName(), "1"));

    }

}
