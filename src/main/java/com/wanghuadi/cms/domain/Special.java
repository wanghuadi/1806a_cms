package com.wanghuadi.cms.domain;

import java.util.Date;

public class Special {
	private Integer id;//主键
	private String title;//标题
	private String abstracts;//摘要
	private Date created;//创建时间
	private Integer acount;//文章数
	
	
	public Integer getAcount() {
		return acount;
	}
	public void setAcount(Integer acount) {
		this.acount = acount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Special(Integer id, String title, String abstracts, Date created) {
		super();
		this.id = id;
		this.title = title;
		this.abstracts = abstracts;
		this.created = created;
	}
	public Special() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Special [id=" + id + ", title=" + title + ", abstracts=" + abstracts + ", created=" + created + "]";
	}
	
}
