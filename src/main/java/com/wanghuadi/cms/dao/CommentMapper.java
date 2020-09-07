package com.wanghuadi.cms.dao;

import java.util.List;

import com.wanghuadi.cms.domain.Comment;

public interface CommentMapper {
	/*
	 * 发表评论
	 */
	public void addComment(Comment comment);
	
	/*
	 * 评论列表
	 */
	public List<Comment> queryCommentByAid(Integer aid);
}
