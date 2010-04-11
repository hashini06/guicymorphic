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
 * Extends {@link org.hibernate.cfg.AnnotationConfiguration} to allow for package scan of all entities in package.
 *
 * @author Alen Vrecko
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
        if (entityPackage == null) {
            throw new NullPointerException("Putting null Package is useless.\nSometimes custom ClassLoader may not provide package information. Therefore " +
                    "YourClass.class.getPackage() returns null. As alternative use scanPackageForAnnotatedClasses(YourClass.class).");
        }
        Set<Class<?>> entities = Classes.matching(Matchers.annotatedWith(javax.persistence.Entity.class).or(Matchers.annotatedWith(Embeddable.class))).in(entityPackage);
        addClasses(entities);
        return this;
    }

    public ExtendedAnnotationConfiguration scanPackageForAnnotatedClasses(Class<?> classToScanPackage) {
        if (classToScanPackage == null) {
            throw new NullPointerException("Argument must not be null.");
        }
        Set<Class<?>> entities = Classes.matching(Matchers.annotatedWith(javax.persistence.Entity.class).or(Matchers.annotatedWith(Embeddable.class))).in(classToScanPackage);
        addClasses(entities);
        return this;
    }


    private void addClasses(Set<Class<?>> entities) {
        for (Class<?> entity : entities) {
            log.info("Adding {} to AnnotationConfiguration.", entity.getName());
            addAnnotatedClass(entity);
        }
    }
}
