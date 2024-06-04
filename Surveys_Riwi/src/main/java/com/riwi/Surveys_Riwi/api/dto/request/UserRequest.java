package com.riwi.Surveys_Riwi.api.dto.request;

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
public class UserRequest {
    @NotBlank(message = ErrorMessages.RequiredName)
    @Size(min = 1, max = 100, message = "The name must have a maximum of 100 characters.")
    private String name;
    @NotBlank(message = ErrorMessages.RequiredEmail)
    @Size(min = 1, max = 100, message = "The email must have a maximum of 100 characters.")
    private String email;
    @NotBlank(message = ErrorMessages.RequiredPassword)
    @Size(min = 1, max = 255, message = "The password must have a maximum of 255 characters.")
    private String password;
    @NotBlank(message = ErrorMessages.RequiredActive)
    private Boolean active;
}
