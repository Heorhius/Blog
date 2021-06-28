package com.kaptsiug.blog.generator;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomCodeGenerator implements Generator<String> {
    @Override
    public String generate(String from) {
        return String.valueOf(UUID.randomUUID());
    }
}
