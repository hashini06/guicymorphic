package guicymorphic.fw.dal.hibernate;

import com.google.inject.Injector;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 28, 2010
 * Time: 3:39:36 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionBubble<T> {

    T doInTransaction(Session session, Injector injector);
}
