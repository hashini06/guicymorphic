package guicymorphic.security;

import com.google.inject.Injector;
import junit.framework.*;

/**
 * todo: write docs
 */
public class GuiceTestSuite extends TestSuite {


    private Injector injector;

    public GuiceTestSuite(Injector injector) {
        this.injector = injector;
    }

    public void setUp(Injector injector) {
    }

    public void tearDown(Injector injector) {
    }

    @Override
    public void run(TestResult result) {
        setUp(injector);
        try {
            result.addListener(new TestListener() {
                public void addError(Test test, Throwable throwable) {
                }

                public void addFailure(Test test, AssertionFailedError assertionFailedError) {
                }

                public void endTest(Test test) {
                }

                public void startTest(Test test) {
                    injector.injectMembers(test);
                }
            });

            super.run(result);
        } finally {
            tearDown(injector);
        }

    }
}
