package com.roleManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roleManagement.Entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	
}
