package com.example.demo.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.OrderTopping;
import com.example.demo.domain.Topping;

@Repository
public class OrderRepository {

	/**
	 * articlesとcommentsテーブルを結合したものからarticleリストを作成する.
	 * articleオブジェクト内にはcommentリストを格納する。
	 */
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		// オーダー情報が入るorderListを生成
		List<Order> orderList = new LinkedList<Order>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		// 前の行のorderIDを退避しておく変数
		long beforeOrderId = 0;

		while (rs.next()) {
			// 現在検索されているorderIdを退避
			int nowOrderId = rs.getInt("o_id");

			// 現在のorderIdと前のorderIdが違う場合はorderオブジェクトを生成
			if (nowOrderId != beforeOrderId) {
				Order order = new Order();
				order.setId(nowOrderId);
				order.setUserId(rs.getInt("o_user_id"));
				order.setStatus(rs.getInt("o_status"));
				order.setTotalPrice(rs.getInt("o_total_price"));
				order.setOrderDate(rs.getDate("o_order_date"));
				order.setDestinationName(rs.getString("o_destination_name"));
				order.setDestinationEmail(rs.getString("o_destination_email"));
				order.setDestinationZipcode(rs.getString("o_destination_zipcode"));
				order.setDestinationAddress(rs.getString("o_destination_address"));
				order.setDistinationTel(rs.getString("o_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("o_delivery_time"));
				order.setPaymentMethod(rs.getInt("o_payment_method"));
				// 空のオーダーアイテムリストを作成しオーダーオブジェクトにセットしておく
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderItemList(orderItemList);
				// オーダーアイテムがセットされていない状態のorderオブジェクトをarticleListオブジェクトにadd
				orderList.add(order);
			}

			// オーダー情報だけあってorderItemがない場合はCommentオブジェクトは作らない
			if (rs.getInt("oi_id") != 0) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				orderItem.setSize(rs.getString("oi_size"));
				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPriceM(rs.getInt("i_price_m"));
				item.setPriceL(rs.getInt("i_price_l"));
				item.setImagePath(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				orderItem.setItem(item);
				orderToppingList = new ArrayList<OrderTopping>();
				orderItem.setOrderToppingList(orderToppingList);
				// オーダーアイテム情報をオーダーオブジェクト内にセットされているorderItemListに直接addしている(参照型なのでこのようなことができる)
				orderItemList.add(orderItem);
			}

			// オーダーアイテム情報だけあってorderToppingがない場合はorderToppingオブジェクトは作らない.
			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPriceM(rs.getInt("t_price_m"));
				topping.setPriceL(rs.getInt("t_price_l"));
				orderTopping.setTopping(topping);
				// オーダートッピング情報をオーダーアイテムオブジェクト内にセットされているorderToppingListに直接addしている(参照型なのでこのようなことができる)
				orderToppingList.add(orderTopping);
			}

			// 現在の記事IDを前の記事IDを入れる退避IDに格納
			beforeOrderId = nowOrderId;
		}
		return orderList;

	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * オーダー情報を追加します.
	 * 
	 * @param order
	 * @return オーダー情報
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		if (order.getId() == null) {
			String sql = "insert into orders(user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method)"
					+ "values(:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:distinationTel,:deliveryTime,:paymentMethod);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = { "id" };
			template.update(sql, param, keyHolder, keyColumnNames);
			order.setId(keyHolder.getKey().intValue());
		} else {
			String sql = "insert into orders(user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method)"
					+ "values(:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:distinationTel,:deliveryTime,:paymentMethod);";
			template.update(sql, param);
		}

		return order;

	}

	/**
	 * ユーザーIDとstatus0の情報を取得します.
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "SELECT\n" + "-- オーダーテーブルカラム\n"
				+ "o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address,\n"
				+ "o.destination_tel o_destination_tel, o.delivery_time o_delivery_time,o.payment_method o_payment_method,\n"
				+ "-- オーダーアイテムテーブルカラム\n"
				+ "oi.id oi_id,oi.item_id oi_item_id, oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size oi_size,\n"
				+ "-- オーダートッピングカラム\n" + "ot.id ot_id, ot.topping_id ot_topping_id,ot.order_item_id ot_order_item_id,\n"
				+ "-- アイテムテーブルカラム\n" + "i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m,\n"
				+ "i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted,\n" + "-- トッピングテーブルカラム\n"
				+ "t.id t_id , t.name t_name, t.price_m t_price_m,t.price_l t_price_l\n" + "FROM\n" + "orders o\n"
				+ "LEFT OUTER JOIN order_items oi ON o.id = oi.order_id\n"
				+ "LEFT OUTER JOIN order_toppings ot ON oi.id = ot.order_item_id\n"
				+ "LEFT OUTER JOIN items i ON oi.item_id = i.id\n"
				+ "LEFT OUTER JOIN toppings t ON ot.topping_id = t.id WHERE o.user_id=:userId AND o.status=:status;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		
		if(orderList.isEmpty()) {
			return null;
		}
		
		return orderList.get(0);
	}

	/**
	 * オーダー情報を更新します.
	 * 
	 * @param order
	 * @return オーダー情報
	 */
	public void update2(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		String updateSql = "UPDATE orders SET id=:id,user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,:destinationName,:destinationEmail,destination_zipcode=:destinationZipcode,destination_address:destinationAddress,"
				+ "destination_tel=:destinationTel,delivery_time=:deliveryTime,payment_method=:paymentMethod";
		template.update(updateSql, param);
	}

	/**
	 * 全件検索を行います.
	 * 
	 * @return
	 */
	public List<Order> findAll() {
		String sql = "SELECT \n"
				+ "o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address, \n"
				+ "o.destination_tel o_destination_tel, o.delivery_time o_delivery_time,o.payment_method o_payment_method,\n"
				+ "oi.id order_item_id, oi.id oi_id,oi.item_id oi_item_id, oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size o_size,\n"
				+ "ot.id ot_id, ot.topping_id ot_topping_id,ot.order_item_id ot_order_item_id,\n"
				+ "i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m,\n"
				+ "i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted,\n"
				+ "t.id t_id , t.name t_name, t.price_m t_price_m,t.price_l t_price_l\n" + "FROM \n" + "orders o\n"
				+ "LEFT OUTER JOIN order_items oi ON o.id = oi.order_id \n"
				+ "LEFT OUTER JOIN order_toppings ot ON oi.id = ot.order_item_id \n"
				+ "LEFT OUTER JOIN items i ON oi.item_id = i.id \n" + "LEFT OUTER JOIN toppings t ON ot.topping_id = t.id;   ";
		List<Order> orderList = template.query(sql, ORDER_RESULT_SET_EXTRACTOR);

		return orderList;
	}

//	修正前　保存用
//	public void update(Order order) {
//		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
//		StringBuilder updateSqlBuilder = new StringBuilder("UPDATE orders");
//		updateSqlBuilder.append("SET user_id=:userId, status=:status, total_price=:totalPrice, ");
//		updateSqlBuilder.append(
//				"order_date=:orderDate, destination_name=:destinationName, destination_email=:destinationEmail, ");
//		updateSqlBuilder.append("destination_zipcode=:destinationZipcode, destination_address=:destinationAddress, ");
//		updateSqlBuilder.append(
//				"destination_tel=:distinationTel, delivery_time=:deliveryTime, payment_method=:paymentMethod, ");
//		updateSqlBuilder.append("WHERE id=:id;");
//		template.update(updateSqlBuilder.toString(), param);
//	}
	
	
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		StringBuilder updateSqlBuilder = new StringBuilder("UPDATE orders ");
		updateSqlBuilder.append("SET status=:status, total_price=:totalPrice, ");
		updateSqlBuilder.append(
				"order_date=:orderDate, destination_name=:destinationName, destination_email=:destinationEmail, ");
		updateSqlBuilder.append("destination_zipcode=:destinationZipcode, destination_address=:destinationAddress, ");
		updateSqlBuilder.append("destination_tel=:distinationTel, delivery_time=:deliveryTime, payment_method=:paymentMethod ");
		updateSqlBuilder.append("WHERE id=:id");
		template.update(updateSqlBuilder.toString(), param);
	}

	public Order load(Integer orderId) {
		String sql = "SELECT \n"
				+ "o.id o_id,o.user_id o_user_id,o.status o_status,o.total_price o_total_price,o.order_date o_order_date,o.destination_name o_destination_name, o.destination_email o_destination_email, o.destination_zipcode o_destination_zipcode, o.destination_address o_destination_address, \n"
				+ "o.destination_tel o_destination_tel, o.delivery_time o_delivery_time,o.payment_method o_payment_method,\n"
				+ "oi.id order_item_id, oi.id oi_id,oi.item_id oi_item_id, oi.order_id oi_order_id,oi.quantity oi_quantity,oi.size oi_size,\n"
				+ "ot.id ot_id, ot.topping_id ot_topping_id,ot.order_item_id ot_order_item_id,\n"
				+ "i.id i_id,i.name i_name, i.description i_description, i.price_m i_price_m,\n"
				+ "i.price_l i_price_l,i.image_path i_image_path, i.deleted i_deleted,\n"
				+ "t.id t_id , t.name t_name, t.price_m t_price_m,t.price_l t_price_l\n" + "FROM \n" + "orders o\n"
				+ "LEFT OUTER JOIN order_items oi ON o.id = oi.order_id \n"
				+ "LEFT OUTER JOIN order_toppings ot ON oi.id = ot.order_item_id \n"
				+ "LEFT OUTER JOIN items i ON oi.item_id = i.id \n"
				+ "LEFT OUTER JOIN toppings t ON ot.topping_id = t.id WHERE o.id = :orderId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		
		if(orderList.isEmpty()) {
			return null;
		}
		return orderList.get(0);
	}
}