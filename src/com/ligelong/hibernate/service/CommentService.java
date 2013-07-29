/**
 * 
 */
package com.ligelong.hibernate.service;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ligelong.hibernate.core.BaseDao;
import com.ligelong.hibernate.core.BaseService;
import com.ligelong.hibernate.entity.CommentEntity;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.entity.UserEntity;

/**
 *<code>CommentService</code>
 *
 * @author David Gong
 */
@Service
@Transactional
public class CommentService extends BaseService<CommentEntity> {
    @Override
    @Resource(name="commentDao")
    public void setBaseDao(BaseDao<CommentEntity> baseDao) {
        this.baseDao = baseDao;
    }
    
    @Transactional
	public void addComment(PostEntity post, String content, UserEntity user) {
    	CommentEntity comment = new CommentEntity();
    	comment.setPost(post);
    	comment.setContent(content);
    	comment.setUser(user);
    	comment.setCreatetime(new Timestamp(System.currentTimeMillis()));
    	this.save(comment);
    }
}
