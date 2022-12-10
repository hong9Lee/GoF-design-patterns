package com.design_patterns.java._02_structural_patterns._07_bridge._04_study;

public class HBPencil extends Brush {

    public static final String type = "[HB 연필]";

    public HBPencil(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return type + " " + color.fill();
    }
}
