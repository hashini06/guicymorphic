package guicymorphic.fw.util;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 27, 2010
 * Time: 7:10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Reflections {

    /**
     * Get the actual type arguments a child class has used to extend a generic
     * base class. (Taken from http://www.artima.com/weblogs/viewpost.jsp?thread=208860. Thanks
     * mathieu.grenonville for finding this solution!)
     *
     * @param baseClass  the base class
     * @param childClass the child class
     * @return a list of the raw classes for the actual type arguments.
     */
    public static <T> List<Class<?>> getTypeArguments(Class<T> baseClass,
                                                      Class<? extends T> childClass) {
        Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
        Type type = childClass;
        // start walking up the inheritance hierarchy until we hit baseClass
        while (!getRawType(type).equals(baseClass)) {
            if (type instanceof Class) {
                // there is no useful information for us in raw types, so just
                // keep going.
                type = ((Class) type).getGenericSuperclass();
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> rawType = (Class) parameterizedType.getRawType();

                Type[] actualTypeArguments = parameterizedType
                        .getActualTypeArguments();
                TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
                for (int i = 0; i < actualTypeArguments.length; i++) {
                    resolvedTypes
                            .put(typeParameters[i], actualTypeArguments[i]);
                }

                if (!rawType.equals(baseClass)) {
                    type = rawType.getGenericSuperclass();
                }
            }
        }

        // finally, for each actual type argument provided to baseClass,
        // determine (if possible)
        // the raw class for that type argument.
        Type[] actualTypeArguments;
        if (type instanceof Class) {
            actualTypeArguments = ((Class) type).getTypeParameters();
        } else {
            actualTypeArguments = ((ParameterizedType) type)
                    .getActualTypeArguments();
        }
        List<Class<?>> typeArgumentsAsClasses = new ArrayList<Class<?>>();
        // resolve types by chasing down type variables.
        for (Type baseType : actualTypeArguments) {
            while (resolvedTypes.containsKey(baseType)) {
                baseType = resolvedTypes.get(baseType);
            }
            typeArgumentsAsClasses.add(getRawType(baseType));
        }
        return typeArgumentsAsClasses;
    }

    // copied and modified from apache commons lang
    // maybe put in utility class

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class<?>) {
            // it is raw, no problem
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            // simple enough to get the raw type of a ParameterizedType
            return (Class<?>) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            // not included in original code, but not too difficult:  just have to get raw component type...
            Class<?> rawComponentType = getRawType(((GenericArrayType) type).getGenericComponentType());
            // ...and know how to reflectively create array types, uncommon but not unheard of:
            return Array.newInstance(rawComponentType, 0).getClass();
        }
        return null;
    }
}
