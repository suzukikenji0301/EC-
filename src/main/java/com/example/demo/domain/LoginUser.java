package com.example.demo.domain;

/**
 * @author kenji.suzuki
 *
 */
public class LoginUser {

	private final User user;
	
	/**
	 * ユーザー情報を保持させる.
	 * 
	 * @param user ユーザー情報。
	 */
	public LoginUser(User user) {
		this.user = user;
	}
	
	/**
	 * ユーザー情報を返します.
	 * 
	 * @return ユーザー情報。
	 */
	public User getUser() {
		return user;
	}
	
}
