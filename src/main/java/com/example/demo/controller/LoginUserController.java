package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.LoginUserForm;
import com.example.demo.service.LoginUserService;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン画面を操作する.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/login")
public class LoginUserController {

	@Autowired
	private LoginUserService loginUserService; 
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String toLogin(LoginUserForm form) {
		return "login";
	}
	/**
	 * ログインします.
	 * 
	 * @return　ログイン画面から商品一覧画面に変遷。
	 */
	@PostMapping("/loginUser")
	public String login() {
	
	return "/showItemList/item_List";
	}
}
