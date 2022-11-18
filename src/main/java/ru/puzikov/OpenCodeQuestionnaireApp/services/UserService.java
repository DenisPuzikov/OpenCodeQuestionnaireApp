package ru.puzikov.OpenCodeQuestionnaireApp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.repositories.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    public static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME = 60 * 1000;
    private final UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);
        user.setPassword(user.getPassword());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = usersRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonLocked(true);
        usersRepository.save(user);
        return true;
    }

    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFailedAttempts() + 1;
        usersRepository.updateFailedAttempts(newFailAttempts, user.getUsername());
    }

    public void resetFailedAttempts(String username) {
        usersRepository.updateFailedAttempts(0, username);
    }

    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        usersRepository.save(user);
    }

    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempts(0);

            usersRepository.save(user);

            return true;
        }
        return false;
    }
}
