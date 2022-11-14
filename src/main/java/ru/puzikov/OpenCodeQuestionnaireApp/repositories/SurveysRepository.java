package ru.puzikov.OpenCodeQuestionnaireApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;

import java.util.List;

@Repository
public interface SurveysRepository extends JpaRepository<Survey, Long> {
    List<Survey> findAllByAuthor(User user);
}
