package org.example.learningtest.template;

import org.assertj.core.api.Assertions;
import org.example.user.template.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;


public class CalcSumTest {
    Calculator calculator;
    String path;
    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
        this.path = getClass().getResource("/numbers.txt").getPath().replace("%20", " ");
    }
    @Test
    public void pathTest(){
        Assertions.assertThat(path).isNotNull();
    }
    @Test
    public void sumOfValues() throws IOException {
        int sum = calculator.calcSum(path);
        Assertions.assertThat(sum).isEqualTo(10);
    }

    @Test
    public void multipleOfValues() throws IOException {
        int multiple = calculator.calcMultiple(path);
        Assertions.assertThat(multiple).isEqualTo(24);
    }

    @Test
    public void concatenateStrings() throws IOException {
        Assertions.assertThat(calculator.concatenate(this.path)).isEqualTo("1234");

    }
}
