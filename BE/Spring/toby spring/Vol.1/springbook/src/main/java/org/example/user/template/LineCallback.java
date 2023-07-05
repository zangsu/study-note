package org.example.user.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
