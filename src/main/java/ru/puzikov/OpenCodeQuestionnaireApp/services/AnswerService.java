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
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswersRepository answersRepository;

    public List<Answer> findAllByQuestionId(long questionId){
        return answersRepository.findAllByQuestionId(questionId);
    }

    public Answer findByAnswerId(long answerId){
        Optional<Answer> foundAnswer = answersRepository.findById(answerId);
        return foundAnswer.orElse(null);
    }

    @Transactional
    public void addNewAnswer(Answer answer){
        answersRepository.save(answer);
    }

    @Transactional
    public void editAnswer(long answerId, Answer answer){
        Answer answerToEdit = answersRepository.findById(answerId).orElseGet(Answer::new);
        if (answerToEdit != null){
            answerToEdit.setContent(answer.getContent());
        }
        answersRepository.save(answerToEdit);
    }

    @Transactional
    public void deleteById(long answerId){
        answersRepository.deleteById(answerId);
    }














}
