package com.park.domain;

/**
 * @author Aaron
 * @since
 */
public class Blog {
	/**
	 * // 文章ID
	 */
	private Integer bid;
	/**
	 * 文章标题
	 */
	private String name;
	/**
	 * 文章作者 ID
	 */
	private Integer authorId;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"bid=" + bid +
				", name='" + name + '\'' +
				", authorId=" + authorId +
				'}';
	}
}
