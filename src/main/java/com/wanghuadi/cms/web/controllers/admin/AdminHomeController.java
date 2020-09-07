/**
 * 
 */
package com.wanghuadi.cms.web.controllers.admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanghuadi.cms.core.Page;
import com.wanghuadi.cms.domain.Article;
import com.wanghuadi.cms.domain.Special;
import com.wanghuadi.cms.service.ArticleService;
import com.wanghuadi.cms.service.SpecialService;
import com.wanghuadi.cms.web.controllers.PassportController;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月29日 下午6:54:11
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {

	public static Logger log = LoggerFactory.getLogger(PassportController.class);
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private SpecialService specialService;
	
	@RequestMapping({"/", "/index"})
	public String home(){
		return "admin/home";
	}
	
	/**
	 * 查询所有的文章信息
	 * 		功能：查询列表  做后台文章审核   
	 * @return
	 */
	@RequestMapping("/articles")
	public String articles(Model model){
		Article article = new Article();
		//查询所有的列表信息
		List<Article> list = articleService.gets(article, null, null);
		
		model.addAttribute("list", list);
		
		return "admin/article-manage";
	}
	 
	/**
	 * 文章审核
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateStatusById")
	@ResponseBody
	public boolean updateStatusById(Integer status,Integer id){
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			map.put("id", id);
			articleService.updateStatus(map );
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	 }
	
	
	/**
	 * 专题管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/specials")
	public String querySpecial(Model model){
		List<Special> list = specialService.querySpecial();
		model.addAttribute("list", list);
		return "/admin/special-manage";
	}
	
	/*
	 * 专题 追加文章
	 */
	@RequestMapping("/querySpecialById")
	public String querySpecialById(Integer sid,Model model){
		//专题回显
		Special special = specialService.querySpecialById(sid);
		
		//该专题下文章列表
		List<Article> articleList = specialService.queryArticleInfoBySid(sid);
		
		model.addAttribute("special", special);
		model.addAttribute("articleList", articleList);
		
		return "/admin/special-edit";
	}
	
	/**
	 * 追加文章
	 * @param sid	专题id
	 * @param aid	文章id
	 * @return
	 */
	@RequestMapping("/specialArticle/addSA")
	@ResponseBody
	public boolean addSA(Integer sid,Integer aid){
		/*
		 * 考虑后期扩展：
		 * 		1.需要验证文章是否不存在
		 * 		2.需要验证该专题下是否已经添加该文章
		 * 		如果以上两个条件 满足 那么不能追加   提示用户   文章不存在 还是已添加
		 */
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("sid", sid);
			map.put("aid", aid);
			specialService.addSA(map );
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
