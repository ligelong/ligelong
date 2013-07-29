/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligelong.hibernate.core.BaseDao;
import com.ligelong.hibernate.core.BaseService;
import com.ligelong.hibernate.entity.LinkEntity;

/**
 * <code>LinkService</code>
 *
 * @author David Gong
 */
@Service
@Transactional
public class LinkService extends BaseService<LinkEntity> {
    /* (non-Javadoc)
     * @see com.ligelong.hibernate.core.BaseService#setBaseDao(com.ligelong.hibernate.core.BaseDao)
     */
    @Override
    @Resource(name="linkDao")
    public void setBaseDao(BaseDao<LinkEntity> baseDao) {
        this.baseDao = baseDao;
    }

    @Transactional
	public void addLink(int parentId, String link, String title, int order, int type) {
		LinkEntity linkEntity = new LinkEntity();
		linkEntity.setParentid(parentId);
		linkEntity.setLink(link);
		linkEntity.setOrder(order);
		linkEntity.setTitle(title);
		linkEntity.setType(type);
		this.save(linkEntity);
	}
}
