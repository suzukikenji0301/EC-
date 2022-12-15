package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Contact;
import com.example.demo.repository.InsertContactRepository;

@Service
@Transactional
public class InsertContactService {

	@Autowired
	private InsertContactRepository insertContactRepository;
	
	/**
	 * お問合せ情報を送信します.
	 * 
	 * @param contact お問合せ情報
	 */
	public void InsertContact(Contact contact) {
		insertContactRepository.insertContact(contact);
	}
}
