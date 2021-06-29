package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEnter {
    @NotEmpty
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;
    @NotEmpty
    private String password;
}
