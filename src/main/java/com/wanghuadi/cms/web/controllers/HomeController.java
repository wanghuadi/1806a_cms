/**
 * 
 */
package com.wanghuadi.cms.web.controllers;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanghuadi.cms.core.Page;
import com.wanghuadi.cms.domain.Article;
import com.wanghuadi.cms.domain.Category;
import com.wanghuadi.cms.domain.Channel;
import com.wanghuadi.cms.domain.Comment;
import com.wanghuadi.cms.domain.Slide;
import com.wanghuadi.cms.domain.User;
import com.wanghuadi.cms.service.ArticleService;
import com.wanghuadi.cms.service.CommentService;
import com.wanghuadi.cms.service.SlideService;
import com.wanghuadi.cms.utils.FileUtils;
import com.wanghuadi.cms.web.Constant;

/**
 * 说明:首页
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午8:19:15
 */
@Controller
public class HomeController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private SlideService slideService;
	
	@Resource
	private CommentService commentService;
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(
			@RequestParam(required = false) Integer channel, //频道
			@RequestParam(required = false) Integer category,//分类
			@RequestParam(defaultValue = "1") Integer page,//分类
			Model model){
		
		//------------------------------------
		Page _page = new Page(page, 30);
		List<Article> articles = null;
		
		//拼条件
		Article conditions = new Article();
		conditions.setDeleted(false);
		conditions.setStatus(1);

		//默认首页显示热门文章
		if(category == null && channel == null){
			conditions.setHot(true);
			
			//热门文章时显示幻灯片
			List<Slide> slides = slideService.getTops(5);
			model.addAttribute("slides", slides);
		}
		
		//如果频道或分类不为空，则显示分类或频道数据
		if(category != null){
			conditions.setCategory(new Category(category));
		}else if(channel != null){
			conditions.setChannel(new Channel(channel));
		}
		
		articles = articleService.gets(conditions, _page, null);
		model.addAttribute("articles", articles);
		

		//---------------右侧放10条最新文章---------------------
		Article lastArticlesConditions = new Article();
		lastArticlesConditions.setDeleted(false);
		lastArticlesConditions.setStatus(1);
		
		Page lastArticlesPage = new Page(1, 10);
		lastArticlesPage.setTotalCount(100);//设置了总记录数，可以节省统计查询，提高性能。
		
		List<Article> lastArticles = articleService.gets(lastArticlesConditions, lastArticlesPage, null);
		model.addAttribute("lastArticles", lastArticles);

		if(channel != null){
			model.addAttribute("channel", new Channel(channel));
		}
		model.addAttribute("category", category);
		
		return "home";
	}
	
	
	/**
	 * 文章详情
	 * @param id  文章id
	 * @return
	 */
	@RequestMapping("/article/{id}")
	public String queryArticleById(@PathVariable("id")Integer id,Model model){
		//文章id有了   详情是不是出来了
		
		//根据文章id  查询文章信息
		Article article = articleService.selectByPrimaryKey(id);
		
		model.addAttribute("article", article);
		
		
		//查询该文章所有的评论信息
		List<Comment> comments = commentService.queryCommentByAid(id);
		model.addAttribute("comments", comments);
		
		//浏览量递增   根据文章id 修改浏览量字段  hits
		articleService.increaseHit(id);
		
		
		//点击量排行
		List<Article> hitsOrder = articleService.hitsOrder();
		model.addAttribute("hitsOrder", hitsOrder);
		
		//评论量排行
		List<Article> commentsOrder = articleService.commentsOrder();
		model.addAttribute("commentsOrder", commentsOrder);
		
		
		return "blog";
	}
	
	/**
	 * 查看图片
	 * @param path  图片路径
	 */
	@RequestMapping("lookImg")
	public void lookImg(String path,HttpServletRequest request,HttpServletResponse response){
		FileUtils.lookImg(path, request, response);
	}
	
	
	/*
	 * 发表评论
	 */
	@RequestMapping("/comment/{aid}")
	@ResponseBody
	public boolean addComment(@PathVariable("aid") Integer aid,String content,HttpSession session){
		
		try {
			//添加评论信息
			//用户信息
			User author = (User) session.getAttribute(Constant.LOGIN_USER);
			
			Comment comment = new Comment();
			comment.setAuthor(author);
			//文章
			Article article = new Article(aid);
			comment.setArticle(article );
			//内容
			comment.setContent(content);
			//时间
			comment.setCreated(new Date());
			
			//发表评论
			commentService.addComment(comment);
			
			
			//评论数递增
			articleService.increaseComments(aid);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	
}
