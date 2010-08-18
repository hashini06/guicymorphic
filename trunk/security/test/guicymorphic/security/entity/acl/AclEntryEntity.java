package guicymorphic.security.entity.acl;

import guicymorphic.security.hibernate.AclHibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 12:11:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AclEntryEntity {

    @Id
    private Long id;

    @ManyToOne(targetEntity = AclTypeLinkEntity.class)
    @AclHibernate.PrincipalType
    private AclTypeLinkEntity principalType;

    @AclHibernate.PrincipalId
    private String principalId;

    @ManyToOne
    @AclHibernate.PermissionType
    private AclTypeLinkEntity permissionType;

    @AclHibernate.PermissionId
    private String permissionId;

    @ManyToOne
    @AclHibernate.ObjectType
    private AclTypeLinkEntity objectType;

    @AclHibernate.ObjectId
    private String objectId;

    @Temporal(TemporalType.TIMESTAMP)
    @AclHibernate.ValidFrom
    private Date validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @AclHibernate.ValidTo
    private Date validTo;

    // IDE GENERATED BELOW THIS POINT

    public AclEntryEntity() {
    }

    public AclEntryEntity(Long id, AclTypeLinkEntity principalType, String principalId, AclTypeLinkEntity permissionType, String permissionId, AclTypeLinkEntity objectType, String objectId, Date validFrom, Date validTo) {
        this.id = id;
        this.principalType = principalType;
        this.principalId = principalId;
        this.permissionType = permissionType;
        this.permissionId = permissionId;
        this.objectType = objectType;
        this.objectId = objectId;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AclTypeLinkEntity getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(AclTypeLinkEntity principalType) {
        this.principalType = principalType;
    }

    public String getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    public AclTypeLinkEntity getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(AclTypeLinkEntity permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public AclTypeLinkEntity getObjectType() {
        return objectType;
    }

    public void setObjectType(AclTypeLinkEntity objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
