/*
 * Created on 2011-10-17
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

import com.ligelong.hibernate.entity.LinkEntity;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.service.LinkService;
import com.ligelong.hibernate.service.PostService;
import com.ligelong.hibernate.service.PostTagService;
import com.ligelong.util.Constants;
import com.ligelong.util.PageUtil;
import com.ligelong.util.Status;
import com.ligelong.util.WebUtil;

/**
 * 
 * <code>IndexController</code>
 *
 * @author David Gong
 */
@Controller
@RequestMapping(value="/")
public class IndexController {
	
    /**
     *
     */
    @RequestMapping(value="/index.do")
	public ModelAndView input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
    	
		Map<String, Object> model = new HashMap<String, Object>();
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
		
		List<LinkEntity> ad1List = linkService.findListByHql(
				"from LinkEntity where parentid=? and type=?", new Object[] {
						1, LinkEntity.Type.LINK.getValue() });
		model.put("ad1", ad1List);
		
		List<LinkEntity> ad2List = linkService.findListByHql(
				"from LinkEntity where parentid=? and type=?", new Object[] {
						2, LinkEntity.Type.LINK.getValue() });
		model.put("ad2", ad2List);
		
		return new ModelAndView("index", model);
	}

    
    @Resource
    private PostService postService;
    @Resource
    private PostTagService postTagService;
    @Resource
    private LinkService linkService;
}
