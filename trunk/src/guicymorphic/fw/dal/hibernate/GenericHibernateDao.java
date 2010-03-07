package guicymorphic.fw.dal.hibernate;

import guicymorphic.fw.dal.GenericDao;
import guicymorphic.fw.util.Reflections;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 6:35:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericHibernateDao<T, ID extends Serializable> extends CommonHibernateDao implements GenericDao<T, ID> {

    protected Class<T> persistentClass = (Class<T>) Reflections.getTypeArguments(GenericHibernateDao.class, this.getClass()).get(0);

    public ID saveThis(T entity) {
        return (ID) save(entity);
    }

    public T getById(ID id) {
        return getById(persistentClass, id);
    }

    public Collection<T> findAll() {
        return createCriteria(persistentClass).list();
    }

    public void deleteById(ID id) {
        deleteById(persistentClass, id);
    }

    public void deleteAll() {
        deleteAll(persistentClass);
    }

    public void deleteAll(Collection<ID> ids) {
        deleteAll(persistentClass, ids);
    }
}
