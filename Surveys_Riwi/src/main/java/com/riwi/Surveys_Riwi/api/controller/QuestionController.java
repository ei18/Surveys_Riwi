package com.riwi.Surveys_Riwi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.Surveys_Riwi.api.dto.request.QuestionRequest;
import com.riwi.Surveys_Riwi.api.dto.request.UserRequest;
import com.riwi.Surveys_Riwi.api.dto.response.QuestionResponse;
import com.riwi.Surveys_Riwi.api.dto.response.UserResponse;
import com.riwi.Surveys_Riwi.infraestructure.abstract_service.IQuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/questions")
@AllArgsConstructor
@Tag(name = "Question")
public class QuestionController {
    @Autowired
    private final IQuestionService questionService;

    @Operation(
        summary = "List all questions with pagination",
        description = "You must submit the page and the page size to get all the corresponding questions")
    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size){
        return  ResponseEntity.ok(this.questionService.getAll(page -1, size));
    }

    @ApiResponse(responseCode = "400", description = "When the id is invalid", content = {
    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    @Operation(
        summary = "List a question by id",
        description = "You must send the id of the question to search for")     
    @GetMapping(path = "{id}")
    public ResponseEntity<QuestionResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(this.questionService.get(id));
    }

    @Operation(
        summary = "Create a question",
        description = "Create a question")  
    @PostMapping
    public ResponseEntity<QuestionResponse> create(@Validated @RequestBody QuestionRequest request){
        return ResponseEntity.ok(this.questionService.create(request));
    }

    @ApiResponse(responseCode = "400", description = "When the request is invalid", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(
        summary = "Update a question",
        description = "Update a question")  
    @PutMapping(path = "{id}")
    public ResponseEntity<QuestionResponse> update(@Validated @RequestBody QuestionRequest request, @PathVariable Long id){
        return ResponseEntity.ok(this.questionService.update(request, id));
    }

    @ApiResponse(responseCode = "400", description = "When the id is invalid", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(
        summary = "Delete a question by id",
        description = "Delete a question by id")  
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
