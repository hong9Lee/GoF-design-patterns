package com.design_patterns.java._03_behavioral_patterns._22_template._02_after;

public class Client {

    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("number.txt");
//        int result = fileProcessor.process((result1, number) -> result1 += number);
//        int result = fileProcessor.process(new Plus());

        int result = fileProcessor.process(new Operator() {
            @Override
            public int getResult(int result, int number) {
                return result += number;
            }
        });
        System.out.println(result);
    }
}
