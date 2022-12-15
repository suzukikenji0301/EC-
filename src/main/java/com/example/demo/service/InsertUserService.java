package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

/**
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class InsertUserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザー情報を挿入します.
	 * 
	 * @param user ユーザー情報。
	 */
	public void InsertUser(User user) {
		userRepository.insert(user);
	}
}
