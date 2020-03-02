package com.roleManagement.service;

import org.springframework.stereotype.Service;

import com.roleManagement.Entity.Message;

@Service
public interface MessageService {
	public Message saveMessage(Message message);

}
