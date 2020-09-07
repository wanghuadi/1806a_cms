package com.wanghuadi.cms.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wanghuadi.cms.core.Page;
import com.wanghuadi.cms.domain.Article;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月28日 下午4:48:47
 */
public interface ArticleService {

	/**
	 * 文章审核
	 * @param map
	 */
	public void updateStatus(Map<String,Object> map);
	
	/**
	 * 功能说明：<br>
	 * @param conditions
	 * @param page
	 * @param orders  排序，默认按创建时间倒排序
	 * @return
	 * List<Article>
	 */
	public abstract List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders);
	
	
	/**
	 * 功能说明：保存文章<br>
	 * @param article
	 * void
	 */
	public void insert(Article article);
	
	
	/**
	 * 文章详情
	 * @param id 文章id
	 * @return
	 */
	public Article selectByPrimaryKey(Integer id);

	
	/**
	 * 功能说明：递增访问量<br>
	 * @param id
	 * void
	 */
	public void increaseHit(int id);
	
	/**
	 * 点击排行
	 * @return
	 */
	public List<Article> hitsOrder();
	
	/**
	 * 评论排行
	 * @return
	 */
	public List<Article> commentsOrder();
	
	/**
	 * 评论数递增
	 * @param id
	 */
	public void increaseComments(Integer id);
	
	public List<Article> articles();
}