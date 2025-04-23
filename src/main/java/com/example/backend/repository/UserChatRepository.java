package com.example.backend.repository;


import com.example.backend.model.chat.Chat;
import com.example.backend.model.message.Message;
import com.example.backend.model.user.User;
import com.example.backend.model.userChat.UserChat;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {

    boolean existsUserChatByUserIdAndChatId(Long userId, Long chatId);

    Long user(@NotNull User user);

    Long chat(@NotNull Chat chat);

    List<UserChat> getUserChatsByUserId(Long userId);

    List<UserChat> getUserChatsByChatId(Long chatId);


    @Query("""
            SELECT c
            FROM user_chat uc
            JOIN chat c ON uc.chatId = chat.id
            WHERE uc.userId IN (:firstUserId, :secondUserId)
                and c.type=1
            GROUP BY c.id
            HAVING count(c.id) = 2
            """)
    Optional<Chat> getUsersPrivateChat(
            @Param("firstUserId") long firstUserId,
            @Param("secondUserId") long secondUserId
    );
}