package guicymorphic.fw.dal.hibernate;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 28, 2010
 * Time: 3:39:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionWrapper {

    private final Provider<Session> sessionProvider;
    private final Injector injector;

    @Inject
    public TransactionWrapper(Provider<Session> sessionProvider, Injector injector) {
        this.sessionProvider = sessionProvider;
        this.injector = injector;
    }

    @Transactional
    public void execute(TransactionalCommand command) {
        command.doInTransaction(sessionProvider.get(), injector);
    }
}
