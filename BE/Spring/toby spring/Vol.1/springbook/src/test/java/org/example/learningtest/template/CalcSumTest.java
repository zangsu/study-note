package org.example.learningtest.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class CalcSumTest {
    @Test
    public void sumOfValues() throws IOException {
        Calculator calculator = new Calculator();
        String path = getClass().getResource("/numbers.txt").getPath().replace("%20", " ");
        Assertions.assertThat(path).isNotNull();
        int sum = calculator.calcSum(path);
        Assertions.assertThat(sum).isEqualTo(10);
    }
}
