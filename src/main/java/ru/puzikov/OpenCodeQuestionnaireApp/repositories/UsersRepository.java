package ru.puzikov.OpenCodeQuestionnaireApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("UPDATE User user set user.failedAttempts = ?1 where user.username = ?2")
    @Modifying
    void updateFailedAttempts(int failAttempts, String username);

}
