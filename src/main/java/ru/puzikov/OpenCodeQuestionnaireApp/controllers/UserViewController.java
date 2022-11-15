package ru.puzikov.OpenCodeQuestionnaireApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.services.SurveyService;
import ru.puzikov.OpenCodeQuestionnaireApp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class UserViewController {

    private final SurveyService surveyService;
    private final UserService userService;

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return surveyService.findAll();
    }

    @GetMapping("/surveys/{id}")
    public Survey getSurveyById(@PathVariable("id") Long surveyId) {
        return surveyService.findById(surveyId);
    }

    @PostMapping("/surveys/{id}")
    public void saveUserAnswer(@PathVariable("id") Long surveyId,
                               @AuthenticationPrincipal User currentUser, List<Answer> answers) {
        Survey surveyFromDb = surveyService.findById(surveyId);
        String username = userService.loadUserByUsername(currentUser.getUsername()).getUsername();

        userService.updateAnswers(username, answers);
        userService.addCompletedSurvey(username, surveyFromDb);
    }
}
