package com.example.backend.blackList;

import com.example.backend.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "black_list")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

