package com.park.domain;

/**
 * @author Aaron
 * @since
 */
public class Author {
	/**
	 * 作者ID
	 */
	private Integer authorId;
	/**
	 * 作者名称
	 */
	private String authorName;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author{" +
				"authorId=" + authorId +
				", authorName='" + authorName + '\'' +
				'}';
	}
}
