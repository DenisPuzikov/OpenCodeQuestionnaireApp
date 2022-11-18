package ru.puzikov.OpenCodeQuestionnaireApp.models;

import lombok.*;
import org.hibernate.Hibernate;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "survey")
public class Survey extends AbstractEntity{

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    @ToString.Exclude
    private List<Question> questions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Survey survey = (Survey) o;
        return getId() != null && Objects.equals(getId(), survey.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
