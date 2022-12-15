package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.form.OrderForm;
import com.example.demo.repository.OrderRepository;

/**
 * 注文情報を操作するサービス.
 * 
 * @author kenji.suzuki
 *
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文します.
	 * 
	 * @param orderForm 注文情報を受け取ったフォーム
	 */
	public void update(OrderForm orderForm) {
		System.out.println(orderForm.intGetOrderId());
		Order order = orderRepository.load(orderForm.intGetOrderId());
		
		 Date dateObj = new Date();
		 order.setOrderDate(dateObj);
		
		System.out.println("おーだーidはこれ=" + order.getId());
		order.setTotalPrice(order.getTotalPrice());
		order.setDestinationName(orderForm.getDestinationName());
		order.setDestinationEmail(orderForm.getDestinationEmail());
		order.setDestinationZipcode(orderForm.getDestinationZipcode());
		order.setDestinationAddress(orderForm.getDestinationAddress());
		order.setDistinationTel(orderForm.getDistinationTel());
		order.setDeliveryTime(orderForm.getDeliveryTimestamp());
		if (orderForm.intGetPaymentMethod() == 1) {
			order.setPaymentMethod(1);
			order.setStatus(1);
		} else if (orderForm.intGetPaymentMethod() == 2) {
			order.setPaymentMethod(2);
			order.setStatus(2);
		}
		orderRepository.update(order);
	}

}
