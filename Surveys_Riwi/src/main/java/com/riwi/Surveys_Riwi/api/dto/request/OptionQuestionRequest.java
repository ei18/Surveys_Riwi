package com.riwi.Surveys_Riwi.api.dto.request;

import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionRequest {
    @NotBlank(message = ErrorMessages.RequiredText)
    private String text;
    @NotBlank(message = ErrorMessages.RequiredActive)
    private Boolean active;
}
