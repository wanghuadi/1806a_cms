/**
 * 
 */
package com.wanghuadi.cms.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanghuadi.cms.core.Page;
import com.wanghuadi.cms.domain.Article;


/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月16日 下午9:18:02
 */
public interface ArticleMapper {
	
	/**
	 * 文章审核
	 * @param map
	 */
	public void updateStatus(Map<String,Object> map);
	

	/**
	 * 功能说明：保存文章<br>
	 * @param article
	 * void
	 */
	public void insert(Article article);
	

	/**
	 * 功能说明：递增访问量<br>
	 * @param id
	 * void
	 */
	public void increaseHit(int id);
	
	
	/**
	 * 功能说明：查询文章<br>
	 * @return
	 * List<Article>
	 */
	public List<Article> selects(@Param("article") Article article, @Param("order") LinkedHashMap<String, Boolean> orders, @Param("page") Page page);
	
	
	/**
	 * 功能说明：统计<br>
	 * @param article
	 * @return
	 * int
	 */
	public int count(@Param("article") Article article);
	
	/**
	 * 文章详情
	 * @param id 文章id
	 * @return
	 */
	public Article selectByPrimaryKey(Integer id);
	
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
