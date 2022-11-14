package ru.puzikov.OpenCodeQuestionnaireApp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "question")
public class Question extends AbstractEntity{

    @Column(name = "condition")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Column(name = "is_multiple_choice")
    private boolean isMultipleChoice = false;
}
