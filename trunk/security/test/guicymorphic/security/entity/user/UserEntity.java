package guicymorphic.security.entity.user;

import guicymorphic.security.hibernate.AclHibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user that may log in and use the application
 */
@Entity
@Table(name = "user")
public class UserEntity {

    private Long id;
    private String username;
    private Set<GroupEntity> groups = new HashSet<GroupEntity>();
    private Set<OrganizationEntity> organizations = new HashSet<OrganizationEntity>();

    /**
     * Default constructor
     */
    public UserEntity() {
    }

    public UserEntity(Long id, String username) {
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

    @OneToMany
    @AclHibernate.AccessControlParent(GroupEntity.class)
    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    @OneToMany
    @AclHibernate.AccessControlParent(OrganizationEntity.class)
    public Set<OrganizationEntity> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<OrganizationEntity> organizations) {
        this.organizations = organizations;
    }
}
