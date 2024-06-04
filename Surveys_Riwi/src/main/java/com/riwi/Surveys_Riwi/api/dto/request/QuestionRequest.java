package com.riwi.Surveys_Riwi.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    @NotBlank(message = "The text is required")
    private String text;
    @NotBlank(message = "The type is required")
    private String type;
    @NotBlank(message = "The active is required")
    private boolean active;
}
