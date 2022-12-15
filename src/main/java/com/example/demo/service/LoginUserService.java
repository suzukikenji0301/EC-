package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class LoginUserService {

	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * ログインします.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ユーザー情報 存在しない場合はnullが返ります
	 */
	public User login(String email, String password) {
		User user = userRepository.findByMailAndPassword(email, password);
//		User user = userRepository.findByMail(email);
		if (user == null) {
			return null;
		}
//		if (!passwordEncoder.matches(password, user.getPassword())) {
//			return null;
//		}
		return user;
	}
}