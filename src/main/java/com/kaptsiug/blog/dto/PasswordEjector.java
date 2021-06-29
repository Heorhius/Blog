package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEjector {
    @NotEmpty
    private String activationCode;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String newPasswordRepeat;

}
