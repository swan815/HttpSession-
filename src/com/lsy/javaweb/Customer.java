package com.lsy.javaweb;

public class Customer {
	private String name;
	private String address;
	private String card;
	private String cardType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Customer(String name, String address, String card, String cardType) {
		super();
		this.name = name;
		this.address = address;
		this.card = card;
		this.cardType = cardType;
	}
	public Customer() {
		
		
	}
	
	
	

}
