package com.park.domain;

import java.util.List;

/**
 * @author Aaron
 * @since
 */
public class BlogAndComment {
	private Integer bid; // 文章ID
	private String name; // 文章标题
	private Integer authorId; // 文章作者ID
	private List<Comment> comment; // 文章评论
}
