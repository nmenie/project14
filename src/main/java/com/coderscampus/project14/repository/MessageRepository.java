package com.coderscampus.project14.repository;

import com.coderscampus.project14.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Custom method to find messages by channel name
    List<Message> findByChannel(String channel);
}
