package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Contact;

/**
 * お問合せ情報を操作するリポジトリ.
 * 
 * @author kenji.suzuki
 *
 */
@Repository
public class InsertContactRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * お問合せ情報を挿入します.
	 * 
	 * @param contact お問合せ情報
	 */
	public void insertContact(Contact contact) {
		
		SqlParameterSource param =new BeanPropertySqlParameterSource(contact);
		String sql = "insert into contacts (name,fullname,mailaddress,telephone,gender,inquiryDetails) values(:name,:fullname,:mailAddress,:telePhone,:gender,:inquiryDetails);";
		System.out.println(contact);
		template.update(sql, param);
	}
}
