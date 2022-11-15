package ru.puzikov.OpenCodeQuestionnaireApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Answer;
import ru.puzikov.OpenCodeQuestionnaireApp.repositories.AnswersRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswersRepository answersRepository;

    public List<Answer> findAllByQuestionId(Long questionId){
        return answersRepository.findAllByQuestionId(questionId);
    }

    public Answer findByAnswerId(Long answerId){
        Optional<Answer> foundAnswer = answersRepository.findById(answerId);
        return foundAnswer.orElse(null);
    }

    public void addNewAnswer(Answer answer){
        answersRepository.save(answer);
    }

    public void editAnswer(Long answerId, Answer answer){
        Answer answerToEdit = answersRepository.findById(answerId).orElseGet(Answer::new);
        if (answerToEdit != null){
            answerToEdit.setContent(answer.getContent());
        }
        answersRepository.save(answerToEdit);
    }

    public void deleteById(Long answerId){
        answersRepository.deleteById(answerId);
    }














}
