package ru.puzikov.OpenCodeQuestionnaireApp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        User user = (User) userService.loadUserByUsername(username);

        if (user != null) {
            if (user.isEnabled() && user.isAccountNonLocked()) {
                if (user.getFailedAttempts() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                    userService.increaseFailedAttempts(user);
                } else {
                    userService.lock(user);
                    exception = new LockedException("Ваш аккаунт был заблокирован из-за неудачных попыток ввода пароля."
                            + " Аккаунт будет разблокирован через 1 минуту.");
                }
            } else if (!user.isAccountNonLocked()) {
                if (userService.unlockWhenTimeExpired(user)) {
                    exception = new LockedException("Ваш аккаунт был разблокирован. Введите логин и пароль.");
                } else {
                    exception = new LockedException("Ваш аккаунт был заблокирован. Вы сможете попробовать снова через 1 минуту.");
                }
            }
        }
        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
