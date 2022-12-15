package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.User;
import com.example.demo.form.InsertUserForm;
import com.example.demo.service.InsertUserService;

/**
 * ユーザー登録画面を操作するコントローラー.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/user")
public class InsertUserController {

	@Autowired
	private InsertUserService insertUserService;
	
	/**
	 * ユーザー登録画面を出力.
	 * 
	 * @return ユーザー登録画面。
	 */
	@RequestMapping("/toInsert")
	public String toResisterAdmin(InsertUserForm form) {
		return "register_admin";
	}
																			
	/**
	 * ユーザー情報を登録します.
	 * 
	 * @param form ユーザー登録用フォーム
	 * @return　ログイン画面
	 */
	@PostMapping("/insert")
	public String insertUser(@Validated InsertUserForm form,BindingResult result) {
		if(result.hasErrors()) {
		System.out.println("form =" + form);	
		return toResisterAdmin(form);
	}
	User user = new User();
	BeanUtils.copyProperties(form, user);
	user.setName(form.getLastName() + form.getFirstName());
	insertUserService.InsertUser(user);
	System.out.println("user =" + user);
	
	return "redirect:/login/";
	
	}
}
