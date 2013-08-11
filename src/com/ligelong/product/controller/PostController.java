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
import com.ligelong.hibernate.entity.LinkEntity;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.entity.UserEntity;
import com.ligelong.hibernate.service.CommentService;
import com.ligelong.hibernate.service.PostService;
import com.ligelong.util.Constants;
import com.ligelong.util.PageUtil;
import com.ligelong.util.Status;
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
    
    @RequestMapping(value="/add.do")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		UserEntity user = (UserEntity)request.getAttribute("user");
		if(user==null) {
			return new UserController().login(request, response);
		}
		if("on".equals(WebUtil.getParameter(request, "action", ""))) {
			String title = WebUtil.getParameter(request, "title", "");
			String text = WebUtil.getParameter(request, "text", "");
			postService.createPost(title, text, user);
			return new ModelAndView("post/add_success", model);
		}
    	return new ModelAndView("post/add", model);
    }
    
    @RequestMapping(value="/today.do")
	public ModelAndView today(HttpServletRequest request,
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
    
    @RequestMapping(value="/top.do")
	public ModelAndView top(HttpServletRequest request,
			HttpServletResponse response) {
    	Integer type = WebUtil.getParameterInteger(request, "type", 1);
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("type", type);
		switch (type) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;

		default:
			break;
		}
		

    	Integer upInteger = WebUtil.getParameterInteger(request, "up", -1);
    	Integer downInteger = WebUtil.getParameterInteger(request, "down", -1);
    	if(upInteger>0) {
    		PostEntity upPostEntity = postService.find(upInteger);
    		if(upPostEntity!=null) {
    			postService.updatePostUpCount(upPostEntity);
    		}
    	}
    	if(downInteger>0) {
    		PostEntity downPostEntity = postService.find(downInteger);
    		if(downPostEntity!=null) {
    			postService.updatePostDownCount(downPostEntity);
    		}
    	}
    	
		int page = WebUtil.getParameterInteger(request, "page", 1);
		int count = postService.findCountByHql(
				"select count(*) from PostEntity where status=?",
				new Object[]{Status.ON.getValue()});
		PageUtil.PageScrollInfo pageScrollInfo = PageUtil.getPageScroll(count,
				page, Constants.PAGE_LENGTH);
		List<PostEntity> postList = postService.findListByHql(
				"from PostEntity where status=?",
				new Object[]{Status.ON.getValue()},
				pageScrollInfo.getCurrentItemStart(), Constants.PAGE_LENGTH);
		for(PostEntity post : postList) {
			post.putTextToContent(postService.getText(post.getId()));
		}
		model.put("list", postList);
		
		model.put("pagenumber", pageScrollInfo.getCurrentPageNumber());
		if(pageScrollInfo.hasNextPage()) {
			model.put("nextpage", pageScrollInfo.getNextPageNumber());
		}
		if(pageScrollInfo.getCurrentPageNumber()>1) {
			model.put("prevpage", pageScrollInfo.getBackPageNumber());
		}
		model.put("totalpage", pageScrollInfo.getPageNumber());
		
    	return new ModelAndView("post/top", model);
    }
    
    @RequestMapping(value="/share.do")
	public ModelAndView share(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("post/share", model);
    }
    
    @Resource
    private PostService postService;
    @Resource
    private CommentService commentService;
}
