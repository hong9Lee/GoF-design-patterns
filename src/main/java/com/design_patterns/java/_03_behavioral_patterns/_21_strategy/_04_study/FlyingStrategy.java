package com.design_patterns.java._03_behavioral_patterns._21_strategy._04_study;

public class FlyingStrategy implements MovingStrategy {

    @Override
    public void move() {
        System.out.println("flying~");
    }
}
