/**
 * 
 */
package com.wanghuadi.cms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 说明:评论表(cms_comment)
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月16日  下午8:34:11
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**主键**/
	private Integer id;
	
	/**内容：大文本字段**/
	private String content;
	
	/**所属分类**/
	private Article article;
	
	/**作者**/
	private User author;

	/**发布时间**/
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Comment(Integer id, String content, Article article, User author, Date created) {
		super();
		this.id = id;
		this.content = content;
		this.article = article;
		this.author = author;
		this.created = created;
	}

	public Comment() {
		super();
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", article=" + article + ", author=" + author
				+ ", created=" + created + "]";
	}

	
}
