package com.riwi.Surveys_Riwi.infraestructure.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Surveys_Riwi.api.dto.request.SurveyRequest;
import com.riwi.Surveys_Riwi.api.dto.response.QuestionResponse;
import com.riwi.Surveys_Riwi.api.dto.response.SurveyResponse;
import com.riwi.Surveys_Riwi.api.dto.response.UserResponse;
import com.riwi.Surveys_Riwi.domain.entities.Survey;
import com.riwi.Surveys_Riwi.domain.entities.User;
import com.riwi.Surveys_Riwi.domain.repositories.SurveyRepository;
import com.riwi.Surveys_Riwi.domain.repositories.UserRepository;
import com.riwi.Surveys_Riwi.infraestructure.abstract_service.ISurveyService;
import com.riwi.Surveys_Riwi.infraestructure.helpers.EmailHelper;
import com.riwi.Surveys_Riwi.utils.exception.BadRequestException;
import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SurveyService implements ISurveyService{
    @Autowired
    private final SurveyRepository surveyRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EmailHelper emailHelper;
    
    @Override
    public SurveyResponse create(SurveyRequest request) {
        User user = this.userRepository.findById(request.getCreatorId()).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("User")));

        Survey survey = this.requestToEntity(request);
        survey.setUser(user);

        if (request.getTitle().equals(findByTitle(request.getTitle()))){
           throw new BadRequestException(ErrorMessages.RequiredTitle);
        }

        if (Objects.nonNull(user.getEmail())) {
            this.emailHelper.sendMail(user.getEmail(), user.getName());
        }

        return this.entityToResponse(this.surveyRepository.save(survey));
    }

    @Override
    public SurveyResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<SurveyResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.surveyRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public SurveyResponse update(SurveyRequest request, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
    
    @Override
    public List<SurveyResponse> findByTitle(String title) {
        return entityToResponseSurvey(this.surveyRepository.findByTitle(title));
    }

    private SurveyResponse entityToResponse(Survey entity) {
       UserResponse user = new UserResponse();
       BeanUtils.copyProperties(entity.getUser(), user); 

       QuestionResponse question = new QuestionResponse();
       BeanUtils.copyProperties(entity.getQuestions(), question);

       return SurveyResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .active(Boolean.TRUE)
                .user(user)
                .build();
    }

    private Survey requestToEntity(SurveyRequest request){
        return Survey.builder()
                .title(request.getTitle())
                .creationDate(LocalDateTime.now())
                .description(request.getDescription())
                .active(Boolean.TRUE)
                .user(User.builder()
                        .id(request.getCreatorId())
                        .build())        
                .build();
    }

    private Survey find(Long id) {
        return this.surveyRepository.findById(id).orElseThrow(() -> new BadRequestException((ErrorMessages.idNotFound("Survey"))));
    }

    private List<SurveyResponse> entityToResponseSurvey (List<Survey> surveys){
        return surveys.stream()
                .map(survey -> SurveyResponse.builder()
                        .title(survey.getTitle())
                        .build()).toList();
    }
}
