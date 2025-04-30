package com.example.backend.model.blackList;

import com.example.backend.model.BaseRdbmsEntity;
import com.example.backend.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "black_list")
public class BlackList extends BaseRdbmsEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocker_id", nullable = false)
    private User blocker;
    @Column(name = "blocker_id", insertable = false, updatable = false)
    private Long blockerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_id", nullable = false)
    private User blocked;
    @Column(name = "blocked_id", insertable = false, updatable = false)
    private Long blockedId;

    public BlackList() {}

    public BlackList(
            User blocker,
            User blocked
    ) {
        this.blocker = blocker;
        this.blocked = blocked;
        this.dateCreated = LocalDateTime.now();
    }
}

