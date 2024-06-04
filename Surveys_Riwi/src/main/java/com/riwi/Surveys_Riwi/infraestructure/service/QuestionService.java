package com.riwi.Surveys_Riwi.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Surveys_Riwi.api.dto.request.QuestionRequest;
import com.riwi.Surveys_Riwi.api.dto.response.QuestionResponse;
import com.riwi.Surveys_Riwi.api.dto.response.UserResponse;
import com.riwi.Surveys_Riwi.domain.entities.Question;
import com.riwi.Surveys_Riwi.domain.entities.User;
import com.riwi.Surveys_Riwi.domain.repositories.QuestionRepository;
import com.riwi.Surveys_Riwi.infraestructure.abstract_service.IQuestionService;
import com.riwi.Surveys_Riwi.utils.exception.BadRequestException;
import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService implements IQuestionService{
    @Autowired
    private final QuestionRepository questionRepository;
    
    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question question = this.requestToEntity(request);
        return this.entityToResponse(this.questionRepository.save(question));
    }

    @Override
    public QuestionResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.questionRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public QuestionResponse update(QuestionRequest request, Long id) {
        Question question = this.find(id);
        Question questionUpdate = this.requestToEntity(request);
        questionUpdate.setId(id);

        return this.entityToResponse(this.questionRepository.save(questionUpdate));
    }

    @Override
    public void delete(Long id) {
        this.questionRepository.delete(this.find(id));;
    }

    private QuestionResponse entityToResponse(Question entity){
        return QuestionResponse.builder()
           .id(entity.getId())
           .text(entity.getText())
           .type(entity.getType())
           .active(entity.getActive())
           .build();
    }

    private Question requestToEntity(QuestionRequest request) {
        return Question.builder()
                .text(request.getText())
                .type(request.getType())
                .build();
    }
    
    private Question find(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new BadRequestException((ErrorMessages.idNotFound("Question"))));
    }
}
