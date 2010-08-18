package guicymorphic.security;

import com.google.common.collect.ImmutableSetMultimap;
import com.google.inject.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 12:36:54
 * To change this template use File | Settings | File Templates.
 */
public class AclDefaultServiceImpl implements AclService {

    private final AclRepository repo;
    private final UserRepository userRepo;
    private final EntityRepository entityRepository;

    @Inject
    public AclDefaultServiceImpl(AclRepository repo, UserRepository userRepo, EntityRepository entityRepository) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.entityRepository = entityRepository;
    }


    public boolean hasPermission(String principalType, String principalId, String objectType, String objectId, String permissionType, String permissionId) {

        // 1.Does the user have the right (allow or deny) specified? If so, apply that right.
        ImmutableSetMultimap<String, String> answer = repo.queryFor(AclQuery.
                filter(AclElement.PRINCIPAL, principalType, principalId).
                filter(AclElement.OBJECT, objectType, objectId).
                filter(AclElement.PERMISSION, permissionType, permissionId).
                select(AclElement.PERMISSION));

        if (answer.size() == 1) {
            return true;
        }

        // 2.If no right is explicitly assigned to the user (unspecified), test all groups to which the user belongs[1].
        // If user u is member of group G, and G is member of group H, this implies that user u is also a member of group H.
        ImmutableSetMultimap<String, String> parentPrincipals = userRepo.findParentPrincipals(principalType, principalId);


        //3.If, at this point, the right is still unspecified, evaluate the right for the parent object as per previous point.
        ImmutableSetMultimap<String, String> parentEntities = entityRepository.findParentEntities(objectType, objectId);


        return false;
    }
}
