package guicymorphic.security;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 11:01:34
 * To change this template use File | Settings | File Templates.
 */
public interface AclService {


    boolean hasPermission(String principalType, String principalId, String objectType, String objectId, String permissionType, String permissionId);


}
