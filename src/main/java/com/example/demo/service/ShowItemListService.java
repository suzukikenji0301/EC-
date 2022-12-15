package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ToppingRepository;

@Service
@Transactional
public class ShowItemListService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 商品情報を取得する.
	 * 
	 * @return 商品情報リスト
	 */
	public List<Item> showItemList(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	
	/**
	 * 商品のあいまい検索を行います.
	 * 
	 * @param name 商品名
	 * @return 商品情報リスト
	 */
	public List<Item> serchByName(String name){
	List<Item> itemList = itemRepository.findByName(name);
	return itemList;
	}
}
