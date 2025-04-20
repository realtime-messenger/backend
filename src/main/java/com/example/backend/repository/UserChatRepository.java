package com.example.backend.repository;


import com.example.backend.model.chat.Chat;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    boolean existsUserChatByUserIdAndChatId(Long userId, Long chatId);

    Long user(@NotNull User user);

    Long chat(@NotNull Chat chat);
}