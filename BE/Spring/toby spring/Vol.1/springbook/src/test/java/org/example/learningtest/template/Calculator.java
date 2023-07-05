package org.example.learningtest.template;

import java.io.*;

public class Calculator {
    public Integer calcSum(String filePath) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            Integer sum = 0;
            while ((line = br.readLine()) != null) {
                sum += Integer.valueOf(line);
            }
            return sum;
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
