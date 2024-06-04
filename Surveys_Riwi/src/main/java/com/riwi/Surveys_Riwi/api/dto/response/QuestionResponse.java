package com.riwi.Surveys_Riwi.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String text;
    private String type;
    private Boolean active;
    private SurveyResponse survey;
    private List<OptionQuestionResponse> options;
}
