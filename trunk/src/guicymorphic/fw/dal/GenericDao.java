package guicymorphic.fw.dal;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 6:35:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDao<T, ID extends Serializable> extends CommonDao {

    // create

    ID saveThis(T entity);

    // read

    T getById(ID id);

    Collection<T> findAll();

    // delete

    void deleteById(ID id);

    void deleteAll();

    void deleteAll(Collection<ID> ids);
}
