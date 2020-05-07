package com.park.domain;

import java.util.List;

/**
 * @author Aaron
 * @since
 */
public class Card {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 类型集合
	 */
	private List<String> cardType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getCardType() {
		return cardType;
	}

	public void setCardType(List<String> cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "Card{" +
				"id=" + id +
				", cardType=" + cardType +
				'}';
	}
}
