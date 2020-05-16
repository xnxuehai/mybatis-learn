package com.park.domain;

import java.io.Serializable;

/**
 * @author Aaron
 * @since
 */
public class Content implements Serializable {

	private Integer id;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Content{" +
				"id=" + id +
				", content='" + content + '\'' +
				'}';
	}
}
