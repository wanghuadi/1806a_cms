package com.wanghuadi.cms.service;

import java.util.List;
import java.util.Map;

import com.wanghuadi.cms.domain.Article;
import com.wanghuadi.cms.domain.Special;

public interface SpecialService {
	//专题列表展示
	public List<Special> querySpecial();
	
	//专题回显
	public Special querySpecialById(Integer id);
	
	//根据专题id  查询该专题下所有的文章信息 
	public List<Article> queryArticleInfoBySid(Integer id);
	
	//添加中间表 给专题追加文章
	public void addSA(Map<String,Object> map);
}
