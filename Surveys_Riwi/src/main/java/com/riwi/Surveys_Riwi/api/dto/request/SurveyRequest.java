package com.riwi.Surveys_Riwi.api.dto.request;

import java.time.LocalDateTime;

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
    @NotBlank(message = "The title is required")
    @Size(min = 1, max = 100, message = "The title must have a maximum of 255 characters.")
    private String title;
    private String description;
    @NotBlank(message = "The creation date is required")
    private LocalDateTime creationDate;
    @NotBlank(message = "The active is required")
    private boolean active;
    private Long creatorId;
    private Long questionId;
}
