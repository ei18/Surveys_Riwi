package com.riwi.Surveys_Riwi.infraestructure.abstract_service;

import com.riwi.Surveys_Riwi.api.dto.request.UserRequest;
import com.riwi.Surveys_Riwi.api.dto.response.UserResponse;

public interface IUserService extends CrudServices<UserRequest, UserResponse, Long>{ 
}
