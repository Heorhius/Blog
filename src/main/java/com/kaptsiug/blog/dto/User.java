package com.kaptsiug.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date createdAt;
}
