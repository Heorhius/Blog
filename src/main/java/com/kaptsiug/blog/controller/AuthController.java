package com.kaptsiug.blog.controller;

import com.kaptsiug.blog.dto.UserEnter;
import com.kaptsiug.blog.dto.UserForm;
import com.kaptsiug.blog.service.RegistrationService;
import com.kaptsiug.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserForm userForm) {
        registrationService.register(userForm);
        return ResponseEntity.ok().build();
    }

    @GetMapping("confirm/${hash}")
    public ResponseEntity<Void> confirmInvitation(@RequestParam String hash) {
        userService.activate(hash);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserEnter userEnter) {
        String enter = registrationService.enter(userEnter);
        return ResponseEntity.ok().body(enter);
    }

}
