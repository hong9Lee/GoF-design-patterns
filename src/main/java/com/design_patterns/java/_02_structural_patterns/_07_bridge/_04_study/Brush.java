package com.design_patterns.java._02_structural_patterns._07_bridge._04_study;

public abstract class Brush {

    protected Color color;

    public Brush(Color color) {
        this.color = color;
    }

    public abstract String draw();
}
