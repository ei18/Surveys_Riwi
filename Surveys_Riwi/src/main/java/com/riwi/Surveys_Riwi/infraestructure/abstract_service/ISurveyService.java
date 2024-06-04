package com.riwi.Surveys_Riwi.infraestructure.abstract_service;

import java.util.List;

import com.riwi.Surveys_Riwi.api.dto.request.SurveyRequest;
import com.riwi.Surveys_Riwi.api.dto.response.SurveyResponse;

public interface ISurveyService extends CrudServices<SurveyRequest, SurveyResponse, Long>{
    List<SurveyResponse> findByTitle(String title);
}
