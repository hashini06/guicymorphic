package guicymorphic.webapp.contacts.server.dal.hibernate;

import com.google.inject.Singleton;
import guicymorphic.fw.dal.hibernate.GenericHibernateDao;
import guicymorphic.webapp.contacts.server.dal.ContactDao;
import guicymorphic.webapp.contacts.server.entity.ContactEntity;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 9:00:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class ContactHibernateDao extends GenericHibernateDao<ContactEntity, Long> implements ContactDao {
}
