package com.roleManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roleManagement.Entity.Message;
import com.roleManagement.Repository.MessageRepository;
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}

}
