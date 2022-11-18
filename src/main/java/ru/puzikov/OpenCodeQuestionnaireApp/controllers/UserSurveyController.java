package ru.puzikov.OpenCodeQuestionnaireApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.services.SurveyService;
import ru.puzikov.OpenCodeQuestionnaireApp.services.UserResultService;

import java.util.List;
import java.util.Map;

@PreAuthorize("hasAuthority('USER')")
@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class UserSurveyController {

    private final SurveyService surveyService;

    private final UserResultService userResultService;

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return surveyService.findAll();
    }

    @GetMapping("/surveys/{id}")
    public Survey getSurveyById(@PathVariable("id") Long surveyId) {
        return surveyService.findById(surveyId);
    }

    @PostMapping("/surveys/{surveyId}")
    public ResponseEntity<Void> saveUserAnswer(@PathVariable("surveyId") Long surveyId,
                                         @AuthenticationPrincipal User currentUser,
                                         @RequestBody Map<String, List<String>> answers) {

        userResultService.saveUserAnswer(surveyId, currentUser, answers);
        return ResponseEntity.ok().build();
    }
}
