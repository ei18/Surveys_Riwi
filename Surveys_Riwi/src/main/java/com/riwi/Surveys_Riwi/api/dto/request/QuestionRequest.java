package com.riwi.Surveys_Riwi.api.dto.request;

import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    @NotBlank(message = ErrorMessages.RequiredText)
    private String text;
    @NotBlank(message = ErrorMessages.RequiredType)
    @Size(min = 1, max = 50, message = "The email must have a maximum of 50 characters.")
    private String type;
    @NotBlank(message = ErrorMessages.RequiredActive)
    private Boolean active;
    private Long surveyId;
    private Long optionQuestionId;
}
