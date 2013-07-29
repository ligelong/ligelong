/**
 * Create on 2011-10-17
 */
package com.ligelong.hibernate.core;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

/**
 * <code>BaseDao</code>
 * 
 * @author David Gong
 */
public abstract class BaseDao<T extends BaseEntity> {
    // 
    protected Class<T> entityClass;

    public BaseDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }

    /**
     * 
     * 
     * @param entity
     */
    public void save(T entity) {
        Assert.notNull(entity, "entity");
        try {
            getSession().save(entity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /**
     * 
     * 
     * @param entity
     */
    public void delete(T entity) {
        Assert.notNull(entity, "entity");
        try {
            getSession().delete(entity);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /*
     * public void update(Object entity) { Assert.notNull(entity, "entity����Ϊ��"); try { getSession().update(entity); }
     * catch (RuntimeException re) { throw re; } }
     */
    /**
     * 
     * 
     * @param id
     * @return 
     */
    @SuppressWarnings("unchecked")
    public T find(Integer id) {
        Assert.notNull(id, "id");
        return (T) getSession().get(this.entityClass, id);
    }

    /**
     * 
     * 
     * @param id
     * @return 
     */
    @SuppressWarnings("unchecked")
    public T find(Long id) {
        Assert.notNull(id, "id");
        return (T) getSession().get(this.entityClass, id);
    }

    /**
     * 
     * 
     * @param id
     * @return 
     */
    @SuppressWarnings("unchecked")
    public T find(String fieldName, Object fieldValue) {
        return (T) HibernateUtils.createCriteria(getSession(), this.entityClass,
                new Criterion[] { Restrictions.eq(fieldName, fieldValue) }).uniqueResult();
    }

    /**
     * 
     * 
     * @return 
     */
    public int findAllCount() {
        Criteria criteria = HibernateUtils.createCriteria(getSession(), this.entityClass, new Criterion[0]);
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    /**
     * 
     * 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return HibernateUtils.createCriteria(getSession(), this.entityClass, new Criterion[0]).list();
    }

    /**
     * 
     * @param start 
     * @param length 
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(int start, int length) {
        Criteria criteria = HibernateUtils.createCriteria(getSession(), this.entityClass, new Criterion[0]);
        criteria.setFirstResult(start);
        criteria.setMaxResults(length);
        return criteria.list();
    }

    /**
     * 
     * 
     * @return 
     */
    public int findCount(String fieldName, Object fieldValue) {
        Criteria criteria = HibernateUtils.createCriteria(getSession(), this.entityClass,
                new Criterion[] { Restrictions.eq(fieldName, fieldValue) });
        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    /**
     *
     *
     * @param fieldName
     * @param fieldValue
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<T> findList(String fieldName, Object fieldValue) {
        Criterion criterion = Restrictions.eq(fieldName, fieldValue);
        return HibernateUtils.createCriteria(getSession(), this.entityClass, new Criterion[] { criterion }).list();
    }

    /**
     * 
     * 
     * @param fieldName
     * @param fieldValue
     * @param start
     * @param length
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findList(String fieldName, Object fieldValue, int start, int length) {
        Criterion criterion = Restrictions.eq(fieldName, fieldValue);
        Criteria criteria = HibernateUtils
                .createCriteria(getSession(), this.entityClass, new Criterion[] { criterion });
        criteria.setFirstResult(start);
        criteria.setFetchSize(length);
        return criteria.list();
    }

    /**
     * 
     * 
     * @param hql hql
     * @param values
     * @return
     */
    public int findCountByHql(String hql, Object[] values) {
        return (new Integer(HibernateUtils.createQuery(getSession(), hql, values).uniqueResult().toString()))
                .intValue();
    }

    /**
     * 
     * 
     * @param hql hql
     * @param values
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<T> findListByHql(String hql, Object[] values) {
        Query q = HibernateUtils.createQuery(getSession(), hql, values);
        return q.list();
    }

    /**
     *
     * 
     * @param hql hql
     * @param values hql
     * @param start
     * @param length
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findListByHql(String hql, Object[] values, int start, int length) {
        Query q = HibernateUtils.createQuery(getSession(), hql, values);
        q.setFirstResult(start);
        q.setMaxResults(length);
        return q.list();
    }

    // session factory
    protected SessionFactory sessionFactory;

    /**
     * 
     * 
     * @param sessionFactory
     */
    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 
     * 
     * @return session
     */
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "DAO for class [" + this.entityClass == null ? "null" : this.entityClass.getName() + "]";
    }
}