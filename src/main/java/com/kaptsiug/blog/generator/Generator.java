package com.kaptsiug.blog.generator;

public interface Generator<T> {
    String generate(T from);
}
