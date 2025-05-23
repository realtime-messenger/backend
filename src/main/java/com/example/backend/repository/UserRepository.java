package com.example.backend.repository;


import com.example.backend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("""
            SELECT u
            FROM users u
            WHERE u.username ILIKE '%' || :query || '%'
            """)
    List<User> findFuzzy(
            @Param("query")
            String query
    );

    Optional<User> getUserById(Long id);
}