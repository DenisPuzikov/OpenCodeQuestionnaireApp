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
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveysRepository surveysRepository;

    public List<Survey> findAll(){
        return surveysRepository.findAll();
    }

    public Survey findById(long id) {
        return surveysRepository.findById(id).get();
    }

    @Transactional
    public void addNewSurvey(Survey survey){
        surveysRepository.save(survey);
    }

    @Transactional
    public void editSurvey(long surveyId, Survey survey){
        Survey surveyToEdit = surveysRepository.findById(surveyId).orElseGet(Survey::new);
        if (surveyToEdit != null){
            surveyToEdit.setTitle(survey.getTitle());
        }
        surveysRepository.save(surveyToEdit);
    }

    @Transactional
    public void delete(long surveyId){
        surveysRepository.deleteById(surveyId);
    }

    public List<Survey> findAllCompletedOfSurvey(long surveyId) {
        Survey survey = surveysRepository.findById(surveyId).get();
        List<User> respondentsOfSurvey = survey.getRespondents();

        List<Survey> completedSurveys = new ArrayList<>();

        respondentsOfSurvey.forEach(
                user -> {
                    completedSurveys.addAll(user.getCompletedSurveys());
        });
        return completedSurveys;
    }

    public List<Survey> findAllCompleted() {
        List<Survey> allSurveys = surveysRepository.findAll();
        List<Survey> completedSurveys = new ArrayList<>();

        allSurveys.forEach(
                survey -> {
                    List<User> respondentsOfSurvey = survey.getRespondents();
                    respondentsOfSurvey.forEach(
                            user -> {
                                completedSurveys.addAll(user.getCompletedSurveys());
            });
        });
        return completedSurveys;
    }














}
