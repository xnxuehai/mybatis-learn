package com.park.mapper;

import com.park.domain.Content;

import java.util.List;

/**
 * @author Aaron
 * @since
 */
public interface ContentMapper {
	/**
	 * 单条插入数据
	 *
	 * @param content
	 */
	void insert(Content content);

	/**
	 * 批量插入数据
	 *
	 * @param list
	 */
	void batchInsert(List<Content> list);

	/**
	 * 按照 id 查询数据
	 *
	 * @param id
	 * @return
	 */
	Content selectById(Integer id);
}
