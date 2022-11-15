package ru.puzikov.OpenCodeQuestionnaireApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Question;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Long> {

    List<Question> findAllBySurveyId(Long id);
}
