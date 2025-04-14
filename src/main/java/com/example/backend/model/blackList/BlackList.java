package com.example.backend.model.blackList;

import com.example.backend.model.BaseEntity;
import com.example.backend.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "black_list")
public class BlackList extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocker_id", nullable = false)
    private User blocker; // Кто добавил в чёрный список

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_id", nullable = false)
    private User blocked; // Кого добавили в чёрный список

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;


    public BlackList() {}

    public BlackList(
            User blocker,
            User blocked
    ) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.dateCreated = LocalDateTime.now();
    }

    public User getBlocker() {
        return this.blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    public User getBlocked() {
        return this.blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getCreatedAt() {
        return dateCreated;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.dateCreated = createdAt;
    }
}

