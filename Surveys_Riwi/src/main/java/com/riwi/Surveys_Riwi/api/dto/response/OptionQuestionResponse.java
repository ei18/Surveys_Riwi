package com.riwi.Surveys_Riwi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionQuestionResponse {
    private Long id;
    private String text;
    private Boolean active;
}
