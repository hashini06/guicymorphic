package guicymorphic.security;

import com.google.common.collect.ImmutableSetMultimap;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 19:38:30
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository {

    ImmutableSetMultimap<String, String> findParentPrincipals(String principalType, String principalId);

}
