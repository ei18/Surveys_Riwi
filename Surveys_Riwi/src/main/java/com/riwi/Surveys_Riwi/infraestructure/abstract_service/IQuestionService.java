package com.riwi.Surveys_Riwi.infraestructure.abstract_service;

import com.riwi.Surveys_Riwi.api.dto.request.QuestionRequest;
import com.riwi.Surveys_Riwi.api.dto.response.QuestionResponse;

public interface IQuestionService extends CrudServices<QuestionRequest, QuestionResponse, Long>{
}
