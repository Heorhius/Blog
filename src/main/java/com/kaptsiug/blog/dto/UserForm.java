package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @NotEmpty
    private String firstName;
    private String lastName;
    @NotEmpty
    private String password;
    @NotEmpty
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;

}
