package ru.puzikov.OpenCodeQuestionnaireApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.UserResult;

import java.util.List;

@Repository
public interface UserResultRepository extends JpaRepository <UserResult, Long> {

    List<UserResult> findAllByUserId(Long userId);

    List<UserResult> findAllBySurveyId(Long surveyId);
}
