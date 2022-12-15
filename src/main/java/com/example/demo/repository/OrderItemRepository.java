package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.OrderItem;

/**
 * order_itemsテーブルを操作するリポジトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class OrderItemRepository {

	/**
	 * 	OrderItemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<OrderItem> ORDER_ITEM_ROW_MAPPER = (rs,i) -> {
		OrderItem orderItem =new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size"));
		return orderItem;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * オーダーアイテムを追加します.
	 * 
	 * @param orderItem 商品一覧
	 * @return　商品一覧
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		
		if(orderItem.getId() == null) {
			String sql = "insert into order_items(item_id,order_id,quantity,size)"
						+ "values(:itemId, :orderId, :quantity, :size);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = {"id"};
			template.update(sql, param, keyHolder, keyColumnNames);
			orderItem.setId(keyHolder.getKey().intValue());
		}else {
			String sql = "insert into order_items(item_id,order_id,quantity,size)"
					+ "values(:itemId, :orderId, :quantity, :size);";
			template.update(sql, param);
		}
			
		return orderItem;
	}
	
	/**
	 * オーダー情報を削除します.
	 * 
	 * @param orderItemId オーダーアイテム
	 */
	public void deleteById(Integer orderItemId) {
		String deleteSql = "DELETE FROM order_items WHERE id=:orderItemID";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemID",orderItemId);
		template.update(deleteSql, param);
	}
	
}
