package com.riwi.Surveys_Riwi.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private Boolean active;
    private UserResponse user;
}
