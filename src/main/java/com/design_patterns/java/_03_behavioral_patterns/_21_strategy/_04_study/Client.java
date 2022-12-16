package com.design_patterns.java._03_behavioral_patterns._21_strategy._04_study;

public class Client {
    public static void main(String[] args) {
        Robot hong = new Person("hong");
        hong.setMovingStrategy(new FlyingStrategy());

        hong.move();
    }
}
