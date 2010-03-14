package guicymorphic.fw.dal.hibernate;

import com.google.inject.Inject;
import com.google.inject.Provider;
import guicymorphic.fw.dal.CommonDao;
import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.hibernate.stat.SessionStatistics;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 6:35:29 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CommonHibernateDao implements CommonDao, Session {

    // todo do something with multiple database support
    // right now it is more trouble than it is worth
    protected Provider<Session> sessionProvider;

    @Inject
    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }


    // defined in CommonDao

    public <E> E getById(Class<E> type, Serializable id) {
        return (E) get(type, id);
    }

    public <E extends Collection> E findAll(Class<E> entityType) {
        return (E) createCriteria(entityType).list();
    }

    public void deleteById(Class<?> entityType, Serializable id) {
        delete(load(entityType, id));
    }

    public void deleteAll(Class<?> entityType) {
        createQuery("delete " + entityType.getName()).executeUpdate();
    }

    public void deleteAll(Class<?> entityType, Collection<? extends Serializable> ids) {
        for (Serializable id : ids) {
            deleteById(entityType, id);
        }
    }

    // auto-generated delegates by IntelliJ IDEA Community Edition to Hibernate Session

    public EntityMode getEntityMode() {
        return sessionProvider.get().getEntityMode();
    }

    public Session getSession(EntityMode entityMode) {
        return sessionProvider.get().getSession(entityMode);
    }

    public void flush() throws HibernateException {
        sessionProvider.get().flush();
    }

    public void setFlushMode(FlushMode flushMode) {
        sessionProvider.get().setFlushMode(flushMode);
    }

    public FlushMode getFlushMode() {
        return sessionProvider.get().getFlushMode();
    }

    public void setCacheMode(CacheMode cacheMode) {
        sessionProvider.get().setCacheMode(cacheMode);
    }

    public CacheMode getCacheMode() {
        return sessionProvider.get().getCacheMode();
    }

    public SessionFactory getSessionFactory() {
        return sessionProvider.get().getSessionFactory();
    }

    public Connection connection() throws HibernateException {
        return sessionProvider.get().connection();
    }

    public Connection close() throws HibernateException {
        return sessionProvider.get().close();
    }

    public void cancelQuery() throws HibernateException {
        sessionProvider.get().cancelQuery();
    }

    public boolean isOpen() {
        return sessionProvider.get().isOpen();
    }

    public boolean isConnected() {
        return sessionProvider.get().isConnected();
    }

    public boolean isDirty() throws HibernateException {
        return sessionProvider.get().isDirty();
    }

    public Serializable getIdentifier(Object o) throws HibernateException {
        return sessionProvider.get().getIdentifier(o);
    }

    public boolean contains(Object o) {
        return sessionProvider.get().contains(o);
    }

    public void evict(Object o) throws HibernateException {
        sessionProvider.get().evict(o);
    }

    public Object load(Class aClass, Serializable serializable, LockMode lockMode) throws HibernateException {
        return sessionProvider.get().load(aClass, serializable, lockMode);
    }

    public Object load(String s, Serializable serializable, LockMode lockMode) throws HibernateException {
        return sessionProvider.get().load(s, serializable, lockMode);
    }

    public Object load(Class aClass, Serializable serializable) throws HibernateException {
        return sessionProvider.get().load(aClass, serializable);
    }

    public Object load(String s, Serializable serializable) throws HibernateException {
        return sessionProvider.get().load(s, serializable);
    }

    public void load(Object o, Serializable serializable) throws HibernateException {
        sessionProvider.get().load(o, serializable);
    }

    public void replicate(Object o, ReplicationMode replicationMode) throws HibernateException {
        sessionProvider.get().replicate(o, replicationMode);
    }

    public void replicate(String s, Object o, ReplicationMode replicationMode) throws HibernateException {
        sessionProvider.get().replicate(s, o, replicationMode);
    }

    public Serializable save(Object o) throws HibernateException {
        return sessionProvider.get().save(o);
    }

    public Serializable save(String s, Object o) throws HibernateException {
        return sessionProvider.get().save(s, o);
    }

    public void saveOrUpdate(Object o) throws HibernateException {
        sessionProvider.get().saveOrUpdate(o);
    }

    public void saveOrUpdate(String s, Object o) throws HibernateException {
        sessionProvider.get().saveOrUpdate(s, o);
    }

    public void update(Object o) throws HibernateException {
        sessionProvider.get().update(o);
    }

    public void update(String s, Object o) throws HibernateException {
        sessionProvider.get().update(s, o);
    }

    public Object merge(Object o) throws HibernateException {
        return sessionProvider.get().merge(o);
    }

    public Object merge(String s, Object o) throws HibernateException {
        return sessionProvider.get().merge(s, o);
    }

    public void persist(Object o) throws HibernateException {
        sessionProvider.get().persist(o);
    }

    public void persist(String s, Object o) throws HibernateException {
        sessionProvider.get().persist(s, o);
    }

    public void delete(Object o) throws HibernateException {
        sessionProvider.get().delete(o);
    }

    public void delete(String s, Object o) throws HibernateException {
        sessionProvider.get().delete(s, o);
    }

    public void lock(Object o, LockMode lockMode) throws HibernateException {
        sessionProvider.get().lock(o, lockMode);
    }

    public void lock(String s, Object o, LockMode lockMode) throws HibernateException {
        sessionProvider.get().lock(s, o, lockMode);
    }

    public void refresh(Object o) throws HibernateException {
        sessionProvider.get().refresh(o);
    }

    public void refresh(Object o, LockMode lockMode) throws HibernateException {
        sessionProvider.get().refresh(o, lockMode);
    }

    public LockMode getCurrentLockMode(Object o) throws HibernateException {
        return sessionProvider.get().getCurrentLockMode(o);
    }

    public Transaction beginTransaction() throws HibernateException {
        return sessionProvider.get().beginTransaction();
    }

    public Transaction getTransaction() {
        return sessionProvider.get().getTransaction();
    }

    public Criteria createCriteria(Class aClass) {
        return sessionProvider.get().createCriteria(aClass);
    }

    public Criteria createCriteria(Class aClass, String s) {
        return sessionProvider.get().createCriteria(aClass, s);
    }

    public Criteria createCriteria(String s) {
        return sessionProvider.get().createCriteria(s);
    }

    public Criteria createCriteria(String s, String s1) {
        return sessionProvider.get().createCriteria(s, s1);
    }

    public Query createQuery(String s) throws HibernateException {
        return sessionProvider.get().createQuery(s);
    }

    public SQLQuery createSQLQuery(String s) throws HibernateException {
        return sessionProvider.get().createSQLQuery(s);
    }

    public Query createFilter(Object o, String s) throws HibernateException {
        return sessionProvider.get().createFilter(o, s);
    }

    public Query getNamedQuery(String s) throws HibernateException {
        return sessionProvider.get().getNamedQuery(s);
    }

    public void clear() {
        sessionProvider.get().clear();
    }

    public Object get(Class aClass, Serializable serializable) throws HibernateException {
        return sessionProvider.get().get(aClass, serializable);
    }

    public Object get(Class aClass, Serializable serializable, LockMode lockMode) throws HibernateException {
        return sessionProvider.get().get(aClass, serializable, lockMode);
    }

    public Object get(String s, Serializable serializable) throws HibernateException {
        return sessionProvider.get().get(s, serializable);
    }

    public Object get(String s, Serializable serializable, LockMode lockMode) throws HibernateException {
        return sessionProvider.get().get(s, serializable, lockMode);
    }

    public String getEntityName(Object o) throws HibernateException {
        return sessionProvider.get().getEntityName(o);
    }

    public Filter enableFilter(String s) {
        return sessionProvider.get().enableFilter(s);
    }

    public Filter getEnabledFilter(String s) {
        return sessionProvider.get().getEnabledFilter(s);
    }

    public void disableFilter(String s) {
        sessionProvider.get().disableFilter(s);
    }

    public SessionStatistics getStatistics() {
        return sessionProvider.get().getStatistics();
    }

    public void setReadOnly(Object o, boolean b) {
        sessionProvider.get().setReadOnly(o, b);
    }

    public void doWork(Work work) throws HibernateException {
        sessionProvider.get().doWork(work);
    }

    public Connection disconnect() throws HibernateException {
        return sessionProvider.get().disconnect();
    }

    public void reconnect() throws HibernateException {
        sessionProvider.get().reconnect();
    }

    public void reconnect(Connection connection) throws HibernateException {
        sessionProvider.get().reconnect(connection);
    }
}
