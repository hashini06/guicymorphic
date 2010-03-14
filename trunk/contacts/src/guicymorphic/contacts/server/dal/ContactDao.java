package guicymorphic.contacts.server.dal;

import com.google.inject.ImplementedBy;
import guicymorphic.fw.dal.GenericDao;
import guicymorphic.contacts.server.dal.hibernate.ContactHibernateDao;
import guicymorphic.contacts.server.entity.ContactEntity;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 6:30:00 PM
 * To change this template use File | Settings | File Templates.
 */
@ImplementedBy(ContactHibernateDao.class)
public interface ContactDao extends GenericDao<ContactEntity, Long> {
}
