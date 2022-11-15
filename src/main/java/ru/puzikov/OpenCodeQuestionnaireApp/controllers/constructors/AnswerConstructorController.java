package ru.puzikov.OpenCodeQuestionnaireApp.controllers.constructors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Question;
import ru.puzikov.OpenCodeQuestionnaireApp.services.AnswerService;
import ru.puzikov.OpenCodeQuestionnaireApp.services.QuestionService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/constructor/answer")
public class AnswerConstructorController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping("/all/{id}")
    public List<Answer> getAllAnswersOfQuestion(@PathVariable("id") long questionId) {
        return answerService.findAllByQuestionId(questionId);
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable("id") long answerId) {
        return answerService.findByAnswerId(answerId);
    }

    @PostMapping("/new/{id}")
    public void addNewAnswer(@PathVariable("id") long questionId, Answer answer) {
        Question currentQuestion = questionService.findByQuestionId(questionId);
        answer.setQuestion(currentQuestion);
        answerService.addNewAnswer(answer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long answerId) {
        answerService.deleteById(answerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public void editAnswer(@PathVariable("id") long answerId, Answer answer) {
        answerService.editAnswer(answerId, answer);
    }
}
