package com.ligelong.hibernate.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * <code>ReflectionUtils</code>
 *
 * @author David Gong
 */
public class ReflectionUtils {
    /**
     * 
     * @param classType
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> Class<T> getSuperClassGenricType(Class classType) {
        return getSuperClassGenricType(classType, 0);
    }

    /**
     * 
     * @param classType
     * @param index
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class getSuperClassGenricType(Class classType, int index) {
        Type genType = classType.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if ((index >= params.length) || (index < 0)) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }
}