package ru.puzikov.OpenCodeQuestionnaireApp.controllers.constructors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Question;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.services.QuestionService;
import ru.puzikov.OpenCodeQuestionnaireApp.services.SurveyService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/constructor/questions")
public class QuestionConstructorController {

    private final QuestionService questionService;
    private final SurveyService surveyService;



    @GetMapping("/all/{id}")
    public List<Question> getAllQuestionsOfSurvey(@PathVariable("id") Long surveyId) {
        return questionService.findAllBySurveyId(surveyId);
    }

    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable("id") Long questionId) {
        return questionService.findByQuestionId(questionId);
    }

    //добавлен параметр вопрос с несколькими ответами или нет
    @PostMapping("/new/{id}")
    public void addNewQuestion(@PathVariable("id") Long surveyId, Question question,
                               @RequestParam(value = "is_multiple_choice") String checkBoxValue) {
        Survey currentSurvey = surveyService.findById(surveyId);

        question.setMultipleChoice(checkBoxValue != null);
        question.setSurvey(currentSurvey);
        questionService.addNewQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long questionId) {
        questionService.deleteById(questionId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public void editSurvey(@PathVariable("id") Long questionId, Question question) {
        questionService.editQuestion(questionId, question);
    }
}
