package com.example.demo.form;

import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 注文時に使うフォーム.
 * 
 * @author kenjisuzuki
 * 
 *
 */
public class OrderForm {

	/** 宛先氏名 */
	@NotBlank(message = "名前を入力して下さい")
	private String destinationName;
	/** 宛先Eメール */
	@Size(min = 1, max = 127, message = "Eメールは１文字以上127文字以内で記載してください")
	@Email(message = "Eメールの形跡が不正です")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message = "郵便番号を入力して下さい")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号形式にしてください")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
	/** 宛先TEL */
	@NotBlank(message = "電話番号を入力して下さい")
	@Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "電話番号を入力して下さい")
	private String distinationTel;
	/** 配達日 */
	@NotBlank(message = "配達日時を入力して下さい")
	private String deliveryDate;
	/** 配達時間 */
	@NotBlank(message = "配達時間を選択して下さい")
	private String deliveryTime;
	/** 支払い方法 */
	@NotBlank(message = "支払いを選択して下さい")
	private String paymentMethod;
	/** オーダーID */
	private String orderId;
	/** クレジットカード番号 */
	@NotBlank(message = "クレジットカード番号を入力して下さい")
	private String card_number;
	/** 有効期限（月） */
	@NotBlank(message = "有効期限（月）を入力して下さい")
	private String card_exp_month;
	/** 有効期限（年） */
	@NotBlank(message = "有効期限（年）を入力して下さい")
	private String card_exp_year;
	/** クレジットカード名義人 */
	@NotBlank(message = "クレジットカード名義を入力して下さい")
	private String card_name;
	/** セキュリティコード */
	@NotBlank(message = "セキュリティコード番号を3桁で入力して下さい")
	private String card_cvv;

	public int getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public int getIntOrderId() {
		return Integer.parseInt(orderId);
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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public Integer intGetPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderId() {
		return orderId;
	}

	public Integer intGetOrderId() {
		return Integer.parseInt(orderId);
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(String card_cvv) {
		this.card_cvv = card_cvv;
	}

	public Timestamp getDeliveryTimestamp() {
		String str = deliveryDate + " " + deliveryTime + ":00:00";
		Timestamp timestamp = Timestamp.valueOf(str);

		return timestamp;
	}

	@Override
	public String toString() {
		return "OrderForm [destinationName=" + destinationName + ", destinationEmail=" + destinationEmail
				+ ", destinationZipcode=" + destinationZipcode + ", destinationAddress=" + destinationAddress
				+ ", distinationTel=" + distinationTel + ", deliveryDate=" + deliveryDate + ", deliveryTime="
				+ deliveryTime + ", paymentMethod=" + paymentMethod + ", orderId=" + orderId + ", card_number="
				+ card_number + ", card_exp_month=" + card_exp_month + ", card_exp_year=" + card_exp_year
				+ ", card_name=" + card_name + ", card_cvv=" + card_cvv + "]";
	}
}