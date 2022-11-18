package ru.puzikov.OpenCodeQuestionnaireApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.puzikov.OpenCodeQuestionnaireApp.models.UserResult;
import ru.puzikov.OpenCodeQuestionnaireApp.services.UserResultService;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class UserResultController {

    private final UserResultService userResultService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public List<UserResult> findAllUserResults() {
        return userResultService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{userId}")
    public List<UserResult> findAllCompletedByUserId(@PathVariable("userId") Long userId) {
        return userResultService.findAllCompletedByUserId(userId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{surveyId}")
    public List<UserResult> findAllCompletedSurveysOfSurvey(@PathVariable("surveyId") Long surveyId) {
        return userResultService.findAllCompletedOfSurvey(surveyId);
    }
}
