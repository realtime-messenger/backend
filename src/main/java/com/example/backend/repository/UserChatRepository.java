package com.example.backend.repository;


import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    boolean existsUserChatByUserIdAndChatId(Long userId, Long chatId);

    Long user(@NotNull User user);

    Long chat(@NotNull Chat chat);
}