package ru.puzikov.OpenCodeQuestionnaireApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(long id);
}
