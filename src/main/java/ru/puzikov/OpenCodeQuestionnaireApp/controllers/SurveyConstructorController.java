package ru.puzikov.OpenCodeQuestionnaireApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.services.SurveyService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/constructor")
public class SurveyConstructorController {

    private final SurveyService surveyService;


    /*
    Действия: получить один/все, создать новый, редактировать, удалить
    Опросник --> Survey
     */
    @GetMapping("/survey/{id}")
    public Survey getOneSurvey(@PathVariable("id") Long surveyId){
        return surveyService.findById(surveyId);
    }

    @GetMapping("/survey")
    public List<Survey> getAllSurveys() {
        return surveyService.findAll();
    }

    @PostMapping("/survey")
    public void addNewSurvey(@AuthenticationPrincipal User currentUser,
                             Survey survey) {
        survey.setAuthor(currentUser);
        surveyService.addNewSurvey(survey);
    }

    @PutMapping("/survey")
    public ResponseEntity<Void> editSurvey(Survey survey) {
        surveyService.editSurvey(survey);
        return ResponseEntity.ok().build();
    }

    //////// ТАКЖЕ ДЕЛАТЬ ВЕЗДЕ где VOID
    @DeleteMapping("/survey/{id}")
    public ResponseEntity<Object> deleteSurveyById(@PathVariable("id") Long surveyId) {
        surveyService.delete(surveyId);
        return ResponseEntity.ok().build();
    }

}
