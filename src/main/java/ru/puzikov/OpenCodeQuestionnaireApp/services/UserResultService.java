package ru.puzikov.OpenCodeQuestionnaireApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.puzikov.OpenCodeQuestionnaireApp.models.UserResult;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.repositories.UserResultRepository;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserResultService {

    private final UserResultRepository userResultRepository;

    public List<UserResult> findAll() {
        return userResultRepository.findAll();
    }

    public List<UserResult> findAllCompletedByUserId(Long userId) {
        return userResultRepository.findAllByUserId(userId);
    }

    public List<UserResult> findAllCompletedOfSurvey(Long surveyId) {
        return userResultRepository.findAllBySurveyId(surveyId);
    }

    public void saveUserAnswer(Long surveyId, User currentUser, Map<String, List<String>> answers) {

        userResultRepository.save(
                UserResult.builder()
                .userId(currentUser.getId())
                .surveyId(surveyId)
                .answers(answers)
                .build());
    }
}
