package com.design_patterns.java._02_structural_patterns._07_bridge._04_study;

public class MonoLine extends Brush {

    public static final String type = "[모노라인]";

    public MonoLine(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return type + " " + color.fill();
    }
}
