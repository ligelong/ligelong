/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligelong.hibernate.core.BaseDao;
import com.ligelong.hibernate.core.BaseService;
import com.ligelong.hibernate.entity.PostTagEntity;

/**
 * <code>PostTagService</code>
 *
 * @author David Gong
 */
@Service
@Transactional
public class PostTagService extends BaseService<PostTagEntity> {
    /* (non-Javadoc)
     * @see com.ligelong.hibernate.core.BaseService#setBaseDao(com.ligelong.hibernate.core.BaseDao)
     */
    @Override
    @Resource(name="postTagDao")
    public void setBaseDao(BaseDao<PostTagEntity> baseDao) {
        this.baseDao = baseDao;
    }
}
