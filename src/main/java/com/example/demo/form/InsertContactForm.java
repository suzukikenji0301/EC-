package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author kenji.suzuki
 *
 */
public class InsertContactForm {

	/** 名前 */
	@NotBlank(message = "苗字の入力は必須です。")
	@Size(max = 10, message = "苗字は10桁以内で入力してください。")
	private String name;

	/** フリガナ */
	@NotBlank(message = "名前の入力は必須です。")
	@Size(max = 10, message = "名前は10桁以内で入力してください。")
	private String fullName;

	/** メールアドレス */
	@NotBlank(message = "Eメールの入力は必須です。")
	@Email(message = "Eメールの形式が不正です。")
	private String mailAddress;

	/** 電話番号 */
	@Pattern(regexp = "^0\\d{2}-\\d{4}-\\d{4}$", message = "電話番号の形式で入力してください。")
	private String telePhone;

	/** 性別 */
	@NotBlank(message = "性別の入力は必須です。")
	private String gender;

	/** お問合せ内容 */
	@NotBlank(message = "お問合せの入力は必須です。")
	@Size(min = 10, max = 500, message = "内容は10文字以上500文字以内で入力してください。")
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
		return "InsertContactForm [name=" + name + ", fullName=" + fullName + ", mailAddress=" + mailAddress
				+ ", telePhone=" + telePhone + ", gender=" + gender + ", inquiryDetails=" + inquiryDetails + "]";
	}

}
