package com.example.demo.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author kenji.suzuki
 *
 */
public class Contact {

	/** 名前 */
	@NotBlank(message = "名前の入力は必須です。")
	@Size(max = 50, message = "名前は10桁以内で入力してください")
	private String name;

	/** フリガナ */
	@NotBlank(message = "フリガナの入力は必須です。")
	@Size(max = 50, message = "フリガナは10桁以内で入力してください")
	private String fullName;

	/** メールアドレス */
	@NotBlank(message = "Eメールの入力は必須です。")
	@Email(message = "Eメールの形式が不正です。")
	private String mailAddress;

	/** 電話番号 */
	@Pattern(regexp = "^0\\d{2,3}-\\d{1,4}-\\d{4}$", message = "電話番号の形式で入力してください")
	private String telePhone;

	/** 性別 */
	private String gender;

	/** お問合せ内容 */
	@NotBlank(message = "お問合せ内容の入力は必須です。")
	private String inquiryDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInquiryDetails() {
		return inquiryDetails;
	}

	public void setInquiryDetails(String inquiryDetails) {
		this.inquiryDetails = inquiryDetails;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", fullName=" + fullName + ", mailAddress=" + mailAddress + ", telePhone="
				+ telePhone + ", gender=" + gender + ", inquiryDetails=" + inquiryDetails + "]";
	}

}
