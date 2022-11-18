package ru.puzikov.OpenCodeQuestionnaireApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puzikov.OpenCodeQuestionnaireApp.models.Survey;
import ru.puzikov.OpenCodeQuestionnaireApp.models.security.User;
import ru.puzikov.OpenCodeQuestionnaireApp.repositories.SurveysRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveysRepository surveysRepository;

    public List<Survey> findAll(){
        return surveysRepository.findAll();
    }

    public Survey findById(Long id) {
        return surveysRepository.findById(id).get();
    }

    public void addNewSurvey(Survey survey){
        surveysRepository.save(survey);
    }

    public void editSurvey(Survey survey){
        surveysRepository.save(survey);
    }

    public void delete(Long surveyId){
        surveysRepository.deleteById(surveyId);
    }

}
