package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.domain.LoginUser;
import com.example.demo.service.ShowItemListService;

/**
 * 商品一覧画面を操作する.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	/**
	 * 商品一覧画面を出力します.
	 * 
	 * @param model
	 * @param loginUser ログインユーザー
	 * @return　商品一覧画面
	 */
	@GetMapping("/")
	public String showItemList(Model model,@AuthenticationPrincipal LoginUser loginUser) {
		List<Item> itemList = showItemListService.showItemList();
		model.addAttribute("itemList", itemList);
		return "item_List";
	}
	
	/**
	 * 商品一覧から名前を検索します.
	 * 
	 * @param name　検索した名前
	 * @return　商品一覧画面
	 */
	@PostMapping("/serchItem")
	public String serchItem(Model model, String name) {
		List<Item> itemList = showItemListService.serchByName(name);
		if (itemList.isEmpty()) {
			model.addAttribute("result", "該当する商品がありません。");
			List<Item> allItemList = showItemListService.showItemList();
			model.addAttribute("itemList", allItemList);
			return "item_list";
		}
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
}

