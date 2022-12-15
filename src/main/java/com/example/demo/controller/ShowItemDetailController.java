package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.form.InsertCartForm;
import com.example.demo.service.ShowItemDetailService;


/**
 * 商品詳細を表示するControllerクラス.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/showItemDetail")
public class ShowItemDetailController {
 
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@GetMapping("/showItemDetail")
	public String showItemDetail(Integer id, Model model,InsertCartForm insertCartForm) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "item_detail";
	}
}
