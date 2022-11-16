package ru.puzikov.OpenCodeQuestionnaireApp.models;

import lombok.Getter;
import lombok.Setter;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "answer")
public class Answer extends AbstractEntity{

    @Column(name = "answer_text")
    private String answerText;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
