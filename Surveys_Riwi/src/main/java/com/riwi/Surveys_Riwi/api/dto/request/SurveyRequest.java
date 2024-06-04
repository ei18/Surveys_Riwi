package com.riwi.Surveys_Riwi.api.dto.request;

import java.time.LocalDateTime;

import com.riwi.Surveys_Riwi.utils.messages.ErrorMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {
    @NotBlank(message = ErrorMessages.RequiredTitle)
    @Size(min = 1, max = 255, message = "The title must have a maximum of 255 characters.")
    private String title;
    private String description;
    @NotBlank(message = ErrorMessages.RequiredDate)
    private LocalDateTime creationDate;
    @NotBlank(message = ErrorMessages.RequiredActive)
    private Boolean active;
    private Long creatorId;
    private Long questionId;
}
