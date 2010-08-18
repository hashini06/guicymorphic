package guicymorphic.security.hibernate;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.MapMaker;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.wideplay.warp.persist.Transactional;
import guicymorphic.fw.dal.hibernate.TransactionBubble;
import guicymorphic.fw.dal.hibernate.TransactionBubbles;
import guicymorphic.security.*;
import org.hibernate.Query;
import org.hibernate.Session;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: avrecko
 * Date: 18.8.2010
 * Time: 12:51:15
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class AclHibernateRepository implements AclRepository, EntityRepository, UserRepository {


    @Inject
    @AclHibernate.EntryEntity
    private String entryEntity;

    @Inject
    @AclHibernate.TypeLinkEntity
    private String typeLinkEntity;

    @Inject
    @AclHibernate.PrincipalType
    private String principalType;

    @Inject
    @AclHibernate.PrincipalId
    private String principalId;

    @Inject
    @AclHibernate.PermissionType
    private String permissionType;

    @Inject
    @AclHibernate.PermissionId
    private String permissionId;

    @Inject
    @AclHibernate.ObjectType
    private String objectType;

    @Inject
    @AclHibernate.ObjectId
    private String objectId;

    @Inject
    @AclHibernate.ValidFrom
    private String validFrom;

    @Inject
    @AclHibernate.ValidTo
    private String validTo;

    @Inject
    @AclHibernate.TypeLinkValue
    private String typeLinkValue;

    @Inject
    private TransactionBubbles bubbles;

    @Inject
    private Provider<Session> session;


    private String elementToIdProperty(AclElement element) {
        if (element == AclElement.OBJECT) {
            return objectId;
        } else if (element == AclElement.PERMISSION) {
            return permissionId;
        } else if (element == AclElement.PRINCIPAL) {
            return principalId;
        }
        throw new IllegalArgumentException();
    }

    private String elementToTypeProperty(AclElement element) {
        if (element == AclElement.OBJECT) {
            return objectType;
        } else if (element == AclElement.PERMISSION) {
            return permissionType;
        } else if (element == AclElement.PRINCIPAL) {
            return principalType;
        }
        throw new IllegalArgumentException();
    }

    @Transactional
    public ImmutableSetMultimap<String, String> queryFor(AclQuery query) {
        checkNotNull(query);

        StringBuilder queryBuild = new StringBuilder();
        queryBuild.append("select e.").append(elementToTypeProperty(query.selectedElement)).append(".").append(typeLinkValue);
        queryBuild.append(", e.").append(elementToIdProperty(query.selectedElement));
        queryBuild.append(" from ").append(entryEntity).append(" e ");

        boolean first = true;
        for (AclQuery.AclQueryEntry objects : query.elements) {
            if (first) {
                queryBuild.append(" where ");
                first = false;
            } else {
                queryBuild.append(" and ");
            }
            String elementId = elementToIdProperty(objects.element);
            queryBuild.append("e.").append(elementId);
            queryBuild.append(" = '");
            queryBuild.append(objects.elementId).append("'");

            queryBuild.append(" and e.");
            queryBuild.append(elementToTypeProperty(objects.element)).append(".").append(typeLinkValue).append("='").append(objects.elementType).append("'");
        }

        Query hqlQuery = session.get().createQuery(queryBuild.toString());

        ImmutableSetMultimap.Builder<String, String> builder = ImmutableSetMultimap.builder();

        for (Object[] entry : (List<Object[]>) hqlQuery.list()) {
            builder.put((String) entry[0], (String) entry[1]);
        }
        return builder.build();

    }


    private static Map<Class<?>, ImmutableMap<String, Class<?>>> accessControlParentProperty = new MapMaker().makeComputingMap(new Function<Class<?>, ImmutableMap<String, Class<?>>>() {
        public ImmutableMap<String, Class<?>> apply(Class<?> aClass) {

            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
                ImmutableMap.Builder<String, Class<?>> builder = new ImmutableMap.Builder();
                for (PropertyDescriptor prop : beanInfo.getPropertyDescriptors()) {
                    Field field = null;
                    Method readMethod = prop.getReadMethod();
                    Method writeMethod = prop.getWriteMethod();
                    try {
                        field = aClass.getDeclaredField(prop.getName());
                    } catch (NoSuchFieldException e) {
                        // no worries maybe it is on the read or write method
                    }

                    if (field != null && field.getAnnotation(AclHibernate.AccessControlParent.class) != null) {
                        builder.put(prop.getName(), field.getAnnotation(AclHibernate.AccessControlParent.class).value());
                    }
                    if (readMethod != null && readMethod.getAnnotation(AclHibernate.AccessControlParent.class) != null) {
                        builder.put(prop.getName(), readMethod.getAnnotation(AclHibernate.AccessControlParent.class).value());
                    }
                    if (writeMethod != null && writeMethod.getAnnotation(AclHibernate.AccessControlParent.class) != null) {
                        builder.put(prop.getName(), writeMethod.getAnnotation(AclHibernate.AccessControlParent.class).value());
                    }


                }

                return builder.build();
            } catch (IntrospectionException e) {
                throw new RuntimeException(e);
            }


        }
    });

    public ImmutableSetMultimap<String, String> findParentEntities(final String objectType, final String objectId) {
        try {
            final Class<?> aClass = Class.forName(objectType);
            final ImmutableMap<String, Class<?>> properties = accessControlParentProperty.get(aClass);

            if (properties.isEmpty()) {
                return ImmutableSetMultimap.of();
            }

            return bubbles.execute(new TransactionBubble<ImmutableSetMultimap<String, String>>() {
                public ImmutableSetMultimap<String, String> doInTransaction(Session session, Injector injector) {
                    ImmutableSetMultimap.Builder<String, String> builder = new ImmutableSetMultimap.Builder();
                    StringBuilder queryBuilder = null;
                    for (Map.Entry<String, Class<?>> property : properties.entrySet()) {
                        queryBuilder = new StringBuilder();
                        if (property.getValue() == Void.class) {
                            queryBuilder.append("select g.id from ").append(objectType).append(" g where g.").append(property.getKey()).append(".id=").append(objectId);
                        } else {
                            queryBuilder.append("select g.id from ").append(objectType).append(" u").append(", ").append(property.getValue().getName()).append(" g where g IN elements(").append("u.").append(property.getKey()).append(")");
                        }


                        String s = queryBuilder.toString();
                        Query session1Query = session.createQuery(s);
                        List list = session1Query.list();
                        for (Object o : list) {
                            if (property.getValue() == Void.class) {
                                builder.put(objectType, String.valueOf(o));
                            } else {
                                builder.put(property.getValue().getName(), String.valueOf(o));
                            }

                        }

                    }
                    return builder.build();
                }
            });

        } catch (Throwable th) {
            throw new RuntimeException(th);
        }


    }


    public ImmutableSetMultimap<String, String> findParentPrincipals(String principalType, String principalId) {
        return findParentEntities(principalType, principalId);
    }
}
