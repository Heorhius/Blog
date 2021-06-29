package com.kaptsiug.blog.controller;

import com.kaptsiug.blog.dto.PasswordRestorer;
import com.kaptsiug.blog.dto.UserEnter;
import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.service.RegistrationService;
import com.kaptsiug.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody UserForm userForm) {
        registrationService.register(userForm);
    }

    @GetMapping("confirm/${hash}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmInvitation(@RequestParam String hash) {
        userService.activate(hash);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserEnter userEnter) {
        String enter = registrationService.enter(userEnter);
        return ResponseEntity.ok().body(enter);
    }

    @PostMapping("/forgot_password")
    @ResponseStatus(HttpStatus.OK)
    public void restorePassword(@Valid @RequestBody PasswordRestorer passwordRestorer) {

    }

    @PostMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@Valid @RequestBody PasswordRestorer passwordRestorer) {

    }

    @GetMapping("/check_code/${code}")
    public String checkCode(@RequestParam String activationCode) {
        return userService.checkCode(activationCode);
    }




}
