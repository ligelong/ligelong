/**
 * Create on 2011-11-17
 */
package com.ligelong.hibernate.core;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

/**
 * 
 * <code>HibernateUtils</code>
 * 
 * @author David Gong
 */
public class HibernateUtils {
    private HibernateUtils() {
    }

    /**
     * 
     * @param session Hibernate session
     * @param entityClass hibernate
     * @param criterions
     * @return
     */
    public static Criteria createCriteria(Session session, Class<?> entityClass, Criterion[] criterions) {
        Criteria criteria = session.createCriteria(entityClass);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return criteria;
    }

    /**
     * 
     * @param session Hibernate session
     * @param hql hql
     * @param values
     * @return Query
     */
    public static Query createQuery(Session session, String hql, Object[] values) {
        Query query = session.createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    /**
     * 
     * @param session Hibernate session
     * @param hql hql
     * @param values
     * @return Query
     */
    public static Query createQuery(Session session, String hql, Map<String, ?> values) {
        Query query = session.createQuery(hql);
        if (values != null) {
            query.setProperties(values);
        }
        return query;
    }
}