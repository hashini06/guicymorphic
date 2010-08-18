package guicymorphic.security;

import com.google.common.collect.ImmutableSetMultimap;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 12:34:49
 * To change this template use File | Settings | File Templates.
 */
public interface AclRepository {

    ImmutableSetMultimap<String, String> queryFor(AclQuery query);

}
