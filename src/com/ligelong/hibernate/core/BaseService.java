/**
 * Create on 2011-10-17
 */
package com.ligelong.hibernate.core;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * <code>BaseService</code>
 * 
 * @author David Gong
 */
@Transactional
public abstract class BaseService<T extends BaseEntity> {
    //
    protected BaseDao<T> baseDao;

    /**
     * 
     * @param baseDao
     */
    public abstract void setBaseDao(BaseDao<T> baseDao);

    /**
     * 
     * @return dao
     */
    public BaseDao<T> getBaseDao() {
        return this.baseDao;
    }

    /**
     * 
     * @param entity
     */
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void save(T entity) {
        getBaseDao().save(entity);
    }

    /**
     * 
     * @param entity
     */
    @Transactional
    public void delete(T entity) {
        getBaseDao().delete(entity);
    }

    /**
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public T find(Long id) {
        return getBaseDao().find(id);
    }

    /**
     * 
     * 
     * @param id
     * @return 
     */
    @Transactional(readOnly = true)
    public T find(Integer id) {
        return getBaseDao().find(id);
    }

    /**
     * 
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public int findAllCount() {
        return getBaseDao().findAllCount();
    }

    /**
     * 
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    /**
     * 
     * 
     * @param start
     * @param length
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findAll(int start, int length) {
        return getBaseDao().findAll(start, length);
    }
    
    /**
     * 
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Transactional(readOnly = true)
    public T find(String fieldName, Object fieldValue) {
        return getBaseDao().find(fieldName, fieldValue);
    }

    /**
     * 
     * 
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Transactional(readOnly = true)
    public int findCount(String fieldName, Object fieldValue) {
        return getBaseDao().findCount(fieldName, fieldValue);
    }

    /**
     * 
     * 
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findList(String fieldName, Object fieldValue) {
        return getBaseDao().findList(fieldName, fieldValue);
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
    @Transactional(readOnly = true)
    public List<T> findList(String fieldName, Object fieldValue, int start, int length) {
        return getBaseDao().findList(fieldName, fieldValue, start, length);
    }

    /**
     * 
     *  
     * @param hql hql
     * @param values
     * @return
     */
    @Transactional(readOnly = true)
    public int findCountByHql(String hql, Object[] values) {
        return getBaseDao().findCountByHql(hql, values);
    }

    /**
     * 
     * 
     * @param hql hql
     * @param values
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findListByHql(String hql, Object[] values) {
        return getBaseDao().findListByHql(hql, values);
    }

    /**
     * 
     * 
     * @param hql hql
     * @param values
     * @param start
     * @param length
     * @return
     */
    @Transactional(readOnly = true)
    public List<T> findListByHql(String hql, Object[] values, int start, int length) {
        return getBaseDao().findListByHql(hql, values, start, length);
    }
}