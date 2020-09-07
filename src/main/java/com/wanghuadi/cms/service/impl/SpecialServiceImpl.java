package com.wanghuadi.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wanghuadi.cms.dao.SpecialMapper;
import com.wanghuadi.cms.domain.Article;
import com.wanghuadi.cms.domain.Special;
import com.wanghuadi.cms.service.SpecialService;

@Service
public class SpecialServiceImpl implements SpecialService {
	
	@Resource
	private SpecialMapper specialMapper;

	@Override
	public List<Special> querySpecial() {
		return specialMapper.querySpecial();
	}

	@Override
	public Special querySpecialById(Integer id) {
		return specialMapper.querySpecialById(id);
	}

	@Override
	public List<Article> queryArticleInfoBySid(Integer id) {
		return specialMapper.queryArticleInfoBySid(id);
	}

	@Override
	public void addSA(Map<String, Object> map) {
		specialMapper.addSA(map);
	}

}
