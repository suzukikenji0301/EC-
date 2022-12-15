package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginUserForm {

	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力して下さい")
	@Email(message = "Eメールの形式が不正です。")
	private String email;
	/** パスワード */
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,24}$", message = "大文字、小文字、数字、記号で入力してください。")
	@Size(min = 8, max = 127, message = "パスワードは8文字以上127文字以内で記載してください。")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUserForm [email=" + email + ", password=" + password + "]";
	}

}
