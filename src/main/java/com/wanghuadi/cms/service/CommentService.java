package com.wanghuadi.cms.service;

import java.util.List;

import com.wanghuadi.cms.domain.Comment;

public interface CommentService {
	/*
	 * 发表评论
	 */
	public void addComment(Comment comment);
	
	/*
	 * 评论列表
	 */
	public List<Comment> queryCommentByAid(Integer aid);
}
