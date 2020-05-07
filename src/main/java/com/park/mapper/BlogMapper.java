package com.park.mapper;


import com.park.domain.AuthorAndBlog;
import com.park.domain.BlogAndAuthor;
import com.park.domain.BlogAndComment;

import java.util.List;

/**
 * @Author: Aaron
 */
public interface BlogMapper {

	/**
	 * 根据博客查询作者，一对一，嵌套结果
	 *
	 * @param bid
	 * @return
	 */
	BlogAndAuthor selectBlogWithAuthorResult(Integer bid);

	/**
	 * 根据博客查询作者，一对一，嵌套查询，存在N+1问题
	 *
	 * @param bid
	 * @return
	 */
	BlogAndAuthor selectBlogWithAuthorQuery(Integer bid);

	/**
	 * 查询文章带出文章所有评论（一对多）
	 *
	 * @param bid
	 * @return
	 */
	BlogAndComment selectBlogWithCommentById(Integer bid);

	/**
	 * 查询作者带出博客和评论（多对多）
	 * @return
	 */
	List<AuthorAndBlog> selectAuthorWithBlog();
}
