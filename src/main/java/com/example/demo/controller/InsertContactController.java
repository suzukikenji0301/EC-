package com.example.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Contact;
import com.example.demo.form.InsertContactForm;
import com.example.demo.service.InsertContactService;

/**
 * お問い合わせフォームを操作するコントローラー.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/contact")
public class InsertContactController {

	@Autowired
	private InsertContactService insertContactService;

	/**
	 * お問合せフォーム画面出力.
	 * 
	 * @param form お問合せフォーム
	 * @return お問合せフォーム画面
	 */
	@RequestMapping("/InsertContact")
	public String toInsertContact(InsertContactForm form) {
		return "contactForm";

	}

	/**
	 * お問合せ情報を登録します.
	 * 
	 * @param form お問い合わせフォーム
	 * @return　完了画面にリダイレクト
	 */
	@PostMapping("/insertC")
	public String insertContact(@Validated InsertContactForm form, BindingResult result) {
		if (result.hasErrors()) {
			return toInsertContact(form);
		}
		Contact contact = new Contact();
		BeanUtils.copyProperties(form, contact);
		insertContactService.InsertContact(contact);
		System.out.println("contact =" + contact);
		return "contact_finish";
	}
}
