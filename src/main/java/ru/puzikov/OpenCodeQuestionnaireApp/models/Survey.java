package ru.puzikov.OpenCodeQuestionnaireApp.models;

import lombok.Data;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "survey")
public class Survey extends AbstractEntity{

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    private List<Question> questions;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_completed_surveys",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> respondents;
}
