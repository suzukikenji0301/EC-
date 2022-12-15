package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Order;
import com.example.demo.form.OrderForm;
import com.example.demo.service.OrderConfirmService;


/**
 * 注文確認画面を操作する.
 * 
 * @author kenji.suzuki
 *
 */
@Controller
@RequestMapping("/orderConfirm")
public class OrderConfirmController {

	@Autowired
	private OrderConfirmService orderConfirmService;

	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param orderId オーダーID
	 * @return 注文確認画面
	 */
	@RequestMapping("/orderConfirm")
	public String orderConfirm(Integer orderId, Model model, OrderForm orderForm) {
		System.out.println(orderId);
		Order order = orderConfirmService.orderConfirm(orderId);
		double tax = order.getTotalPrice();
		tax = tax / 1.1;
		tax = tax * 0.1;
		model.addAttribute("order",order);
		model.addAttribute("tax", tax);
		model.addAttribute("totalPrice", order.getTotalPrice());
		model.addAttribute("order", order);
		return "order_confirm";
	}
}