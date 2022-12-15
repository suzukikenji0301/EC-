package com.example.demo.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * 注文情報を表すdomainクラス.
 * 
 * @author kenji.suzuki
 *
 */
public class Order {

	/** 注文ID */
	private Integer id;
	
	/** ユーザーID */
	private Integer userId;
	
	/** ステータス */
	private Integer status;
	
	/** 合計金額 */
	private Integer totalPrice;
	
	/** 注文日 */
	private Date orderDate;
	
	/** 宛先氏名 */
	private String destinationName;
	
	/** 宛先Eメール */
	private String destinationEmail;
	
	/** 宛先郵便番号 */
	private String destinationZipcode;
	
	/** 宛先住所 */
	private String destinationAddress;
	
	/** 宛先TEL */
	private String distinationTel;
	
	/** 配達時間 */
	private Timestamp deliveryTime;
	
	/** 支払い方法 */
	private Integer paymentMethod;
	
	/** ユーザー */
	private User user;
	
	/** 注文商品リスト */
	private List<OrderItem> orderItemList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDistinationTel() {
		return distinationTel;
	}

	public void setDistinationTel(String distinationTel) {
		this.distinationTel = distinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", distinationTel=" + distinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + "]";
	}

}
