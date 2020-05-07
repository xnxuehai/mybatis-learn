package com.park.mapper;

import com.park.domain.Card;

import java.util.List;


/**
 * @Author: Aaron
 */
public interface CardMapper {
	/**
	 * 查询所有
	 *
	 * @return
	 */
	List<Card> selectAll();

	/**
	 * 添加一条数据
	 *
	 * @param card
	 */
	void save(Card card);

	/**
	 * 批量添加数据
	 *
	 * @param cardList
	 */
	void batchInsert(List<Card> cardList);
}
