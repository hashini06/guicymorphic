package guicymorphic.fw.dal.hibernate;

import com.google.inject.matcher.Matchers;
import guicymorphic.fw.util.Classes;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.SettingsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Embeddable;
import java.util.Properties;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 20, 2010
 * Time: 1:07:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtendedAnnotationConfiguration extends AnnotationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ExtendedAnnotationConfiguration.class);

    public ExtendedAnnotationConfiguration() {
    }

    public ExtendedAnnotationConfiguration(SettingsFactory sf) {
        super(sf);
    }

    public static ExtendedAnnotationConfiguration fromProperties(Properties properties) {
        return (ExtendedAnnotationConfiguration) new ExtendedAnnotationConfiguration().
                setProperties(properties);
    }

    public ExtendedAnnotationConfiguration scanPackageForAnnotatedClasses(Package entityPackage) {


        Set<Class<?>> entities = Classes.matching(Matchers.annotatedWith(javax.persistence.Entity.class).or(Matchers.annotatedWith(Embeddable.class))).in(entityPackage);

        for (Class<?> entity : entities) {
            log.info("Adding {} to AnnotationConfiguration.", entity.getName());
            addAnnotatedClass(entity);
        }
        return this;
    }
}
