/*
 * Created on 2011-08-20
 */
package com.ligelong.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ligelong.hibernate.entity.CommentEntity;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.service.CommentService;
import com.ligelong.hibernate.service.PostService;
import com.ligelong.util.WebUtil;

/**
 * <code>PostController</code>
 *
 * @author David Gong
 */
@Controller
@RequestMapping(value="/post/")
public class PostController {
    @RequestMapping(value="/post.do")
	public ModelAndView post(HttpServletRequest request,
			HttpServletResponse response) {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Integer id = WebUtil.getParameterInteger(request, "id", 0);
    	PostEntity post = postService.find(id);
    	Integer up = WebUtil.getParameterInteger(request, "up", -1);
    	if(up>0) {
    		post = postService.find(up);
    		if(post!=null) {
    			postService.updatePostUpCount(post);
    		}
    	}
    	Integer down = WebUtil.getParameterInteger(request, "down", -1);
    	if(down>0) {
    		post = postService.find(down);
    		if(post!=null) {
    			postService.updatePostDownCount(post);
    		}
    	}
    	if(post!=null) {
    		if("on".equals(WebUtil.getParameter(request, "action", ""))) {
    			String content = WebUtil.getParameter(request, "content", "");
    			if(content.length()>0) {
    				commentService.addComment(post, content, null);
    				postService.updatePostCommentCount(post);
    			}
    		}
    		post.putTextToContent(postService.getText(post.getId()));
    		model.put("post", post);
    		List<CommentEntity> commentEntities = commentService.findListByHql("from CommentEntity where post.id=?", new Object[]{post.getId()});
    		model.put("comments", commentEntities);
    	}
    	return new ModelAndView("post/post", model);
    }
    
    @Resource
    private PostService postService;
    @Resource
    private CommentService commentService;
}
