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
public class TransactionBubbles {

    private final Provider<Session> sessionProvider;
    private final Injector injector;

    @Inject
    public TransactionBubbles(Provider<Session> sessionProvider, Injector injector) {
        this.sessionProvider = sessionProvider;
        this.injector = injector;
    }

    @Transactional
    public <T> T execute(TransactionBubble<T> command) {
        return command.doInTransaction(sessionProvider.get(), injector);
    }
}
