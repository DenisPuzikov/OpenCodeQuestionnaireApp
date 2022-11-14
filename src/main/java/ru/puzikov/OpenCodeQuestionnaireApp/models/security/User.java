package ru.puzikov.OpenCodeQuestionnaireApp.models.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.puzikov.OpenCodeQuestionnaireApp.models.AbstractEntity;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "survey_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Survey> createdSurveys;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_completed_surveys",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private List<Survey> completedSurveys;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_answers", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answers;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Column(name = "failed_attempts")
    private int failedAttempts;

    @Column(name = "lock_time")
    private Date lockTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
