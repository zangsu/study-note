package org.example.user.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(final String filepath) throws IOException {
        return lineReadTemplate(filepath, 0, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        });
    }

    public int calcMultiple(final String filepath) throws IOException {
        return lineReadTemplate(filepath, 1, new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        });
    }

    public String concatenate(String filepath) throws IOException {
        return lineReadTemplate(filepath, "", new LineCallback<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        });
    }
    public <T> T lineReadTemplate(String filePath, T init, LineCallback<T> callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            T result = init;
            while((line = br.readLine()) != null){
                result = callback.doSomethingWithLine(line, result);
            }
            return result;
        }catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }finally {
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
