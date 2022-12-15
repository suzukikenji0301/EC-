package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.form.InsertCartForm;
import com.example.demo.service.ShopCartService;

import jakarta.servlet.http.HttpSession;

/**
 * 商品表示画面を操作する.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/shopCart")
public class ShopCartController {

	@Autowired
	private HttpSession session;
	@Autowired
	private ShopCartService shopCartService;

	/**
	 * 一覧を表示させます.
	 * 
	 * @param model
	 * @param loginUser ユーザー情報。
	 * @return カートリスト。
	 */
	@GetMapping("/shopCartList")
	public String shopCartList(Model model, LoginUser loginUser) {

		User user = null;
		Order order = null;

		if (loginUser != null) {
			user = loginUser.getUser();
		}

		if (user != null) {
			order = shopCartService.showCartList(user.getId());
		} else {
			order = shopCartService.showCartList(session.hashCode());
		}

		System.out.println("コントローラーの処理" + session.hashCode());

		System.out.println("----------" + order.toString());

		double tax = order.getTotalPrice();
		tax = tax / 1.1;
		tax = tax * 0.1;
		model.addAttribute("order", order);
		model.addAttribute("tax", tax);
		model.addAttribute("totalPrice", order.getTotalPrice());

		return "cart_list";
	}

	/**
	 * オーダーアイテム情報の追加を行います.
	 * 
	 * @param insertCartForm
	 * @param itemId
	 * @param loginUser
	 * @return ショップカートリスト
	 */
	@PostMapping("/insertItem")
	private String insertItem(InsertCartForm insertCartForm, Integer itemId, LoginUser loginUser) {
		insertCartForm.setItemId(itemId);
		insertCartForm.setQuantity(insertCartForm.getQuantity());
		insertCartForm.setSize(insertCartForm.getSize());
		insertCartForm.setToppingList(insertCartForm.getToppingList());
		shopCartService.insertItem(insertCartForm, loginUser);
		return "redirect:/shopCart/showCartList";
	}

	/**
	 * オーダーアイテム情報を削除します.
	 * 
	 * @param orderItemId
	 * @return
	 */
	@PostMapping("/deleteItem")
	private String deleteItem(Integer orderItemId) {
		shopCartService.deleteItem(orderItemId);
		return "redirect:/shopCart/showCartList";
	}
}
