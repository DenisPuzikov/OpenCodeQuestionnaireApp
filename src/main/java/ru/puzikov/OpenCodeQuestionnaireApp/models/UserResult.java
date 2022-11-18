package ru.puzikov.OpenCodeQuestionnaireApp.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "user_result")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResult extends AbstractEntity{


    @Column
    private Long userId;

    @Column
    private Long surveyId;


    //ключ текст вопроса - значение выбранный(ые) ответы
    @Type(type="json")
    @Column(columnDefinition = "json")
    @Basic
    private Map<String, List<String>> answers;

}
