package guicymorphic.security;

import com.google.common.collect.ImmutableSetMultimap;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 21:06:49
 * To change this template use File | Settings | File Templates.
 */
public interface EntityRepository {

    ImmutableSetMultimap<String, String> findParentEntities(String objectType, String objectId);
}
