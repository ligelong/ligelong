/*
 * Created on 2011-08-20
 */
package com.ligelong.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ligelong.hibernate.entity.LinkEntity;
import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.entity.PostTagEntity;
import com.ligelong.hibernate.service.LinkService;
import com.ligelong.hibernate.service.PostService;
import com.ligelong.hibernate.service.PostTagService;
import com.ligelong.util.Constants;
import com.ligelong.util.FileUploader;
import com.ligelong.util.PageUtil;
import com.ligelong.util.WebUtil;

/**
 * <code>AdminController</code>
 *
 * @author David Gong
 */
@Controller
@RequestMapping(value="/yladmin/")
public class AdminController {
    
    @RequestMapping("index.do")
    public ModelAndView index(HttpServletRequest request) {
        return new ModelAndView("/admin/index");
    }

    @RequestMapping("status.do")
    public ModelAndView create(HttpServletRequest request) {
        return new ModelAndView("/admin/status");
    }
    
    @RequestMapping("title.do")
    public ModelAndView title(HttpServletRequest request) {
        return new ModelAndView("/admin/title");
    }
    
    @RequestMapping("sub.do")
    public ModelAndView sub(HttpServletRequest request) {
        return new ModelAndView("/admin/sub");
    }
    
    @RequestMapping("main.do")
    public ModelAndView main(HttpServletRequest request) {
        return new ModelAndView("/admin/main");
    }
    
    @RequestMapping("manage_post.do")
	public ModelAndView managePost(HttpServletRequest request) {
		int page = WebUtil.getParameterInteger(request, "page", 1);
		int count = postService.findAllCount();
		PageUtil.PageScrollInfo pageScrollInfo = PageUtil.getPageScroll(count,
				page, Constants.PAGE_LENGTH);
		List<PostEntity> postList = postService.findAll(
				pageScrollInfo.getCurrentItemStart(), Constants.PAGE_LENGTH);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("list", postList);
		model.put("pagenumber", pageScrollInfo.getCurrentPageNumber());
		if(pageScrollInfo.hasNextPage()) {
			model.put("nextpage", pageScrollInfo.getNextPageNumber());
		}
		if(pageScrollInfo.getCurrentPageNumber()>1) {
			model.put("prevpage", pageScrollInfo.getBackPageNumber());
		}
		model.put("totalpage", pageScrollInfo.getPageNumber());
		return new ModelAndView("/admin/manage_post", model);
	}
    
    @RequestMapping("get_post_content.do")
    public ModelAndView getPostContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	PostEntity post = postService.getPostWithContent(id);
    	String content = "";
    	if(post!=null&&post.gainTextFromContent()!=null) {
    		content = post.gainTextFromContent();
    	}
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print(content);
    	return null;
    }
    
    @RequestMapping("prohibit_post.do")
    public ModelAndView prohibitPostContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	PostEntity post = postService.getPostWithContent(id);
    	String content = "0";
    	if(post!=null) {
    		postService.prohibit(post);
    	}
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print(content);
    	return null;
    }
    
    @RequestMapping("delete_post.do")
    public ModelAndView deletePostContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	PostEntity post = postService.getPostWithContent(id);
    	String content = "0";
    	if(post!=null) {
    		postService.delete(post);
    	}
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print(content);
    	return null;
    }

    @RequestMapping("create_post.do")
    public ModelAndView createPost(HttpServletRequest request) {
    	if("on".equals(WebUtil.getParameter(request, "action", ""))) {
    		String title = WebUtil.getParameter(request, "title", "");
    		String content = WebUtil.getParameter(request, "content", "");
    		PostEntity pe = postService.createPost(title, content, null);
    		return new ModelAndView("/admin/create_post_result", "title", pe.getTitle());
    	} else {
    		List<PostTagEntity> tagList = postTagService.findAll();
    		return new ModelAndView("/admin/create_post", "list", tagList);
    	}
    }
    
    @RequestMapping("import_post.do")
    public ModelAndView importPost(HttpServletRequest request) {
        return new ModelAndView("/admin/import_post");
    }
    
    @RequestMapping("import_save.do")
    public ModelAndView importAndSave(HttpServletRequest request) {
        String fileName = null;
        try {
            Map<String, List<String>> map = FileUploader.upload(request);
            String uploadFileName = map.get("updateFile").get(0);
            if(new File(uploadFileName).exists()) {
                fileName = uploadFileName;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if(fileName==null) {
            return new ModelAndView("/admin/import_post","error","文件上传失败");
        }
        
        return new ModelAndView("/admin/import_save", "file", importPost(new File(fileName)));
    }
    
    private int importPost(File file) {
        XSSFWorkbook xwb;
        try {
            InputStream in = new FileInputStream(file);
            xwb = new XSSFWorkbook(in);
        
        //
        XSSFSheet sheet = xwb.getSheetAt(0);
        //
        XSSFRow row;
        int rowCount = sheet.getPhysicalNumberOfRows();
        //
        int count=0;
        for (int i = sheet.getFirstRowNum(); i < rowCount; i++) {
            row = sheet.getRow(i);
            String content = row.getCell(0).getStringCellValue();
            System.out.println(content);
            if(content!=null && content.trim().length()>10) {
            	postService.createPost(content.trim().substring(0, 5), content, null);
            	count++;
            }
        }
        return count;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    @RequestMapping("manage_ad.do")
    public ModelAndView manageAd(HttpServletRequest request) {
    	int id = WebUtil.getParameterInteger(request, "id", -1);
    	List<LinkEntity> linkList = linkService.findListByHql("from LinkEntity where parentid=?", new Object[]{id});
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("list", linkList);
    	model.put("id", id);
        return new ModelAndView("/admin/manage_ad", model);
    }
    
    @RequestMapping("add_ad_position.do")
    public ModelAndView addAdPosition(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	String title = WebUtil.getParameter(request, "title", "");
    	linkService.addLink(id, null, title, 0, LinkEntity.Type.POSITION.getValue());
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print("0");
    	return null;
    }

    @RequestMapping("add_ad.do")
    public ModelAndView addAd(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	String title = WebUtil.getParameter(request, "title", "");
    	String link = WebUtil.getParameter(request, "link", "");
    	int order = WebUtil.getParameterInteger(request, "order", 0);
    	linkService.addLink(id, link, title, order, LinkEntity.Type.LINK.getValue());
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print("0");
    	return null;
    }


    @RequestMapping("get_ad_content.do")
    public ModelAndView getAdContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	int id = WebUtil.getParameterInteger(request, "id", 0);
    	LinkEntity link = linkService.find(id);
    	String content = "";
    	if(link!=null&&link.getLink()!=null) {
    		content = link.getLink();
    	}
    	WebUtil.setResponseNoCache(response);
    	response.getWriter().print(content);
    	return null;
    }
    
    @RequestMapping("manage_user.do")
    public ModelAndView manageUser(HttpServletRequest request) {
        return new ModelAndView("/admin/manage_user");
    }
    
    @RequestMapping("acl_user.do")
    public ModelAndView aclUser(HttpServletRequest request) {
        return new ModelAndView("/admin/acl_user");
    }
    
    @RequestMapping("statistics.do")
    public ModelAndView statistics(HttpServletRequest request) {
        return new ModelAndView("/admin/statistics");
    }
    
    
    @Resource
    private PostService postService;
    @Resource
    private PostTagService postTagService;
    @Resource
    private LinkService linkService;
}
