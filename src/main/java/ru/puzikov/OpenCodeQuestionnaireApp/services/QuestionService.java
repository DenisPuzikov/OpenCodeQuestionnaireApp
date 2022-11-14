package ru.puzikov.OpenCodeQuestionnaireApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Question;
import ru.puzikov.OpenCodeQuestionnaireApp.repositories.QuestionsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionsRepository questionsRepository;

    public List<Question> findAllBySurveyId(long surveyId){
        return questionsRepository.findAllBySurveyId(surveyId);
    }

    public Question findByQuestionId(long questionId){
        Optional<Question> foundQuestion = questionsRepository.findById(questionId);
        return foundQuestion.orElse(null);
    }

    @Transactional
    public void addNewQuestion(Question question){
        questionsRepository.save(question);
    }

    @Transactional
    public void deleteById(long questionId){
        questionsRepository.deleteById(questionId);
    }

    @Transactional
    public void editQuestion(long questionId, Question question){
        Question editedQuestion = questionsRepository.findById(questionId).orElseGet(Question::new);
        if (editedQuestion != null){
            editedQuestion.setCondition(question.getCondition());
            editedQuestion.setMultipleChoice(question.isMultipleChoice());
        }
        questionsRepository.save(editedQuestion);
    }















}
