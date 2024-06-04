package com.riwi.Surveys_Riwi.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.Surveys_Riwi.api.dto.request.QuestionRequest;
import com.riwi.Surveys_Riwi.api.dto.response.QuestionResponse;
import com.riwi.Surveys_Riwi.domain.entities.Question;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public QuestionResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public QuestionResponse update(QuestionRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    private Question find(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new BadRequestException((ErrorMessages.idNotFound("Course"))));
    }
}
