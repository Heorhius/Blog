package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String password;
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;
    private Date createdAt;
}
