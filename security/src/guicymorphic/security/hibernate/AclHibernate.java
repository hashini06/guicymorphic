package guicymorphic.security.hibernate;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Module;
import com.google.inject.spi.Message;
import guicymorphic.security.AclRepository;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 13:42:26
 * To change this template use File | Settings | File Templates.
 */
public class AclHibernate {

    public static Module buildModule(final Class<?> aclEntryEntity, final Class<?> aclTypeLink) {
        return new AbstractModule() {
            @Override
            protected void configure() {
                bindConstant().annotatedWith(EntryEntity.class).to(aclEntryEntity.getSimpleName());
                bindConstant().annotatedWith(TypeLinkEntity.class).to(aclTypeLink.getSimpleName());
                List<Class<? extends Annotation>> bindingAnnotations = asList(PrincipalType.class, PrincipalId.class, PermissionType.class, PermissionId.class, ObjectType.class, ObjectId.class, ValidFrom.class, ValidTo.class);


                try {
                    BeanInfo beanInfo = Introspector.getBeanInfo(aclEntryEntity);
                    PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
                    // the annotation can be on field, read or write method

                    for (PropertyDescriptor prop : props) {
                        // try finding a field with the same name
                        final Method readMethod = prop.getReadMethod();
                        final Method writeMethod = prop.getWriteMethod();
                        Field field = null;
                        try {
                            field = aclEntryEntity.getDeclaredField(prop.getName());
                        } catch (NoSuchFieldException e) {
                            // no worries maybe it is on the read or write method
                        }

                        for (Class<? extends Annotation> annotation : bindingAnnotations) {

                            if (field != null && field.getAnnotation(annotation) != null ||
                                    readMethod != null && readMethod.getAnnotation(annotation) != null ||
                                    writeMethod != null && writeMethod.getAnnotation(annotation) != null) {

                                bindConstant().annotatedWith(annotation).to(prop.getName());

                            }


                        }
                    }
                } catch (IntrospectionException e) {
                    addError(new Message(asList(), "Failed to introspect " + aclEntryEntity.getSimpleName() + " for AclHibernate annotations", e));
                }

                try {
                    BeanInfo beanInfo = Introspector.getBeanInfo(aclTypeLink);
                    PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
                    // the annotation can be on field, read or write method

                    for (PropertyDescriptor prop : props) {
                        // try finding a field with the same name
                        final Method readMethod = prop.getReadMethod();
                        final Method writeMethod = prop.getWriteMethod();
                        Field field = null;
                        try {
                            field = aclTypeLink.getDeclaredField(prop.getName());
                        } catch (NoSuchFieldException e) {
                            // no worries maybe it is on the read or write method
                        }

                        if (field != null && field.getAnnotation(TypeLinkValue.class) != null ||
                                readMethod != null && readMethod.getAnnotation(TypeLinkValue.class) != null ||
                                writeMethod != null && writeMethod.getAnnotation(TypeLinkValue.class) != null) {
                            bindConstant().annotatedWith(TypeLinkValue.class).to(prop.getName());
                        }

                    }
                } catch (IntrospectionException e) {
                    addError(new Message(asList(), "Failed to introspect " + aclTypeLink.getSimpleName() + " for AclHibernate annotations", e));
                }


                bind(AclRepository.class).to(AclHibernateRepository.class);
            }
        };

    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface EntryEntity {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface TypeLinkEntity {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface PrincipalType {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface PrincipalId {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface PermissionType {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface PermissionId {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface ObjectType {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface ObjectId {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface ValidFrom {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface ValidTo {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface TypeLinkValue {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @BindingAnnotation
    public @interface AccessControlParent {
        Class<?> value() default Void.class;
    }

}
