package com.park.domain;

/**
 * @author Aaron
 * @since
 */
public class BlogAndAuthor {
	/**
	 * 文章ID
	 */
	private Integer bid;
	/**
	 * 文章标题
	 */
	private String name;
	/**
	 * 作者
	 */
	private Author author;

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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BlogAndAuthor{" +
				"bid=" + bid +
				", name='" + name + '\'' +
				", author=" + author +
				'}';
	}
}
