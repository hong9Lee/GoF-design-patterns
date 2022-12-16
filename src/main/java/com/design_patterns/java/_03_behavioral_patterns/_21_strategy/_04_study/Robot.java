package com.design_patterns.java._03_behavioral_patterns._21_strategy._04_study;

public class Robot {
    private String name;
    private MovingStrategy movingStrategy;

    public Robot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void move() {
        movingStrategy.move();
    }
}
