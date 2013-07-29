/*
 * Created on 2011-08-20
 */
package com.ligelong.hibernate.service;

import java.io.File;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligelong.hibernate.core.BaseDao;
import com.ligelong.hibernate.core.BaseService;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.entity.UserEntity;
import com.ligelong.util.Status;
import com.ligelong.util.Util;

/**
 * <code>PostService</code>
 *
 * @author David Gong
 */
@Service
@Transactional
public class PostService extends BaseService<PostEntity> {
    /* (non-Javadoc)
     * @see com.ligelong.hibernate.core.BaseService#setBaseDao(com.ligelong.hibernate.core.BaseDao)
     */
    @Override
    @Resource(name="postDao")
    public void setBaseDao(BaseDao<PostEntity> baseDao) {
        this.baseDao = baseDao;
    }
    
    /**
     * 
     * @param title
     * @param text
     * @param user
     * @return
     */
    public PostEntity createPost(String title, String text, UserEntity user) {
        PostEntity pe = new PostEntity();
        pe.setTitle(title);
        pe.setCreator(user);
        pe.setCreatetime(new Timestamp(System.currentTimeMillis()));
        pe.setStatus(Status.ON.getValue());
        pe.setUpcount(0L);
        pe.setDowncount(0L);
        pe.setCommentcount(0L);
        save(pe);
        saveText(pe.getId(), text);
        return pe;
    }
    
    private void saveText(Integer id, String text) {
    	String fileName = Util.getFileNameById(id);
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(
					new File(fileName), text, "utf-8");
		} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * 
     * @param id
     * @return
     */
    public PostEntity getPostWithContent(Integer id) {
    	PostEntity post = this.find(id);
    	if(post==null)
    		return post;
    	post.putTextToContent(getText(id));
    	return post;
    }
    
    public String getText(Integer id) {
    	String fileName = Util.getFileNameById(id);
		try {
			return org.apache.commons.io.FileUtils.readFileToString(
					new File(fileName), "utf-8");
		} catch (Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }

	public void prohibit(PostEntity p) {
		Session s = this.baseDao.getSession();
		PostEntity post = (PostEntity) s.get(PostEntity.class, p.getId());
		post.setStatus(Status.OFF.getValue());
		s.save(post);
	}

	public void updatePostUpCount(PostEntity p) {
		Session s = this.baseDao.getSession();
		PostEntity post = (PostEntity) s.get(PostEntity.class, p.getId());
		post.setUpcount(post.getUpcount()+1);
		s.save(post);
	}

	public void updatePostDownCount(PostEntity p) {
		Session s = this.baseDao.getSession();
		PostEntity post = (PostEntity) s.get(PostEntity.class, p.getId());
		post.setDowncount(post.getDowncount()+1);
		s.save(post);
	}

	public void updatePostCommentCount(PostEntity p) {
		Session s = this.baseDao.getSession();
		PostEntity post = (PostEntity) s.get(PostEntity.class, p.getId());
		post.setCommentcount(post.getCommentcount()+1);
		s.save(post);
	}
}
