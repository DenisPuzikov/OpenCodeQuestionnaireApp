package ru.puzikov.OpenCodeQuestionnaireApp.controllers.constructors;

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
@RequestMapping("/constructor/survey")
public class SurveyConstructorController {

    private final SurveyService surveyService;

    @GetMapping("/all")
    public List<Survey> getAllSurveys() {
        return surveyService.findAll();
    }

    @PostMapping("/new")
    public void addNewSurvey(@AuthenticationPrincipal User currentUser,
                             Survey survey) {
        survey.setAuthor(currentUser);
        surveyService.addNewSurvey(survey);
    }

    @PutMapping("/edit/{id}")
    public void editSurvey(@PathVariable("id") Long surveyId, Survey survey) {
        surveyService.editSurvey(surveyId, survey);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long surveyId) {
        surveyService.delete(surveyId);
        return ResponseEntity.ok().build();
    }
}
