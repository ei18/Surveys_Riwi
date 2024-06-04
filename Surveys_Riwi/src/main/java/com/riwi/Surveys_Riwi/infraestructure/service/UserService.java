package com.riwi.Surveys_Riwi.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Surveys_Riwi.api.dto.request.UserRequest;
import com.riwi.Surveys_Riwi.api.dto.response.UserResponse;
import com.riwi.Surveys_Riwi.domain.entities.User;
import com.riwi.Surveys_Riwi.domain.repositories.UserRepository;
import com.riwi.Surveys_Riwi.infraestructure.abstract_service.IUserService;
import com.riwi.Surveys_Riwi.utils.exception.BadRequestException;
import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        User user = this.requestToEntity(request);
        return this.entityToResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
        if (page < 0) page = 0;
        PageRequest pagination = PageRequest.of(page, size);

        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        User user = this.find(id);
        User userUpdate = this.requestToEntity(request);
        userUpdate.setId(id);

        return this.entityToResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {
        this.userRepository.delete(this.find(id));;
    }

    private UserResponse entityToResponse(User entity){
        return UserResponse.builder()
           .id(entity.getId())
           .name(entity.getName())
           .email(entity.getEmail())
           .password(entity.getPassword())
           .active(entity.getActive())
           .build();
    }

    private User requestToEntity(UserRequest request){
        return User.builder()
           .name(request.getName())
           .email(request.getEmail())
           .password(request.getPassword())
           .active(request.getActive())
           .build();
    }

    private User find(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("User")));
    }
}
