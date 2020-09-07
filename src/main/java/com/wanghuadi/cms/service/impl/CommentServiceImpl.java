package com.wanghuadi.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wanghuadi.cms.dao.CommentMapper;
import com.wanghuadi.cms.domain.Comment;
import com.wanghuadi.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	
	@Override
	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
	}

	@Override
	public List<Comment> queryCommentByAid(Integer aid) {
		return commentMapper.queryCommentByAid(aid);
	}

}
