package guicymorphic.fw.dal;

import java.io.Serializable;
import java.util.Collection;


public interface CommonDao {

    // create

    Serializable save(Object entity);

    void saveOrUpdate(Object entity);

    // read

    <E> E getById(Class<E> type, Serializable id);

    <E extends Collection> E findAll(Class<E> entityType);

    // update

    void update(Object entity);

    // delete

    void delete(Object entity);

    void deleteById(Class<?> entityType, Serializable id);

    void deleteAll(Class<?> entityType);

    void deleteAll(Class<?> entityType, Collection<? extends Serializable> ids);

}
