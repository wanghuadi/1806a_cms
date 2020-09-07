/**
 * 
 */
package com.wanghuadi.cms.web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wanghuadi.cms.domain.Article;
import com.wanghuadi.cms.domain.Category;
import com.wanghuadi.cms.domain.Channel;
import com.wanghuadi.cms.domain.User;
import com.wanghuadi.cms.service.ArticleService;
import com.wanghuadi.cms.service.ChannelCategoryService;
import com.wanghuadi.cms.utils.FileUtils;
import com.wanghuadi.cms.web.Constant;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午2:40:38
 */
@Controller
@RequestMapping("/my")
public class UserController {
	
	@Resource
	private ChannelCategoryService channelCategoryService;
	
	@Resource
	private ArticleService articleService;

	@RequestMapping({"/", "/index", "/home"})
	public String home(){
		return "user-space/home";
	}
	
	@RequestMapping({"/profile"})
	public String profile(){
		return "user-space/profile";
	}
	
	/**
	 * 跳转文章发布页面
	 * @return
	 */
	@RequestMapping("/blog/edit")
	public String toBlogEdit(Model model){
		//绑定文章对象
		model.addAttribute("article", new Article());
		
		//查询所有的栏目信息
		List<Channel> channels = channelCategoryService.getChannels();
		model.addAttribute("channels", channels);
		
		return "user-space/blog_edit";
	}
	
	
	/**
	 * 根据栏目id 查询栏目下所有分类信息
	 * @param channeId  栏目id
	 * @return
	 */
	@RequestMapping("/category/queryCategoryByChannelId")
	@ResponseBody
	public List<Category> queryCategoryByChannelId(Integer channeId){
		//返回分类信息
		List<Category> categories = channelCategoryService.getCategories(channeId);
		return categories;
	}
	
	/**
	 * 发布文章
	 * @return
	 * @throws Exception 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/blog/save")
	public String blogSave(@Valid Article article,BindingResult result,MultipartFile file,HttpSession session) throws IllegalStateException, Exception{
		
		try {
			//判断验证是否通过
			if(result.hasErrors()){
				//不通过   返回文章发布页面
				return "user-space/blog_edit";
			}
			
			//通过
			
			//通过工具类实现图片上传   返回图片路径
			String upload = FileUtils.upload(file);
			//设置图片路径
			article.setPicture(upload);
			//设置作者/发布人
			User author = (User) session.getAttribute(Constant.LOGIN_USER);
			article.setAuthor(author);
			//设置发布时间
			article.setCreated(new Date());
			//设置是否有效
			article.setDeleted(false);
			//设置默认点击量
			article.setHits(0);
			
			
			//发布文章
			articleService.insert(article);
			
			//发布成功
			return "redirect:/my/blogs";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "user-space/blog_edit";
		}
	}
	
	/*
	 * 查询所有的文章
	 */
	@RequestMapping("/blogs")
	public String blogs(Model model){
		List<Article> articleList = articleService.articles();
		model.addAttribute("articleList", articleList);
		return "user-space/blog_list";
	}
	
}
