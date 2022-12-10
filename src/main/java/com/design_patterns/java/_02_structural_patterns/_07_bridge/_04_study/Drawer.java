package com.design_patterns.java._02_structural_patterns._07_bridge._04_study;

public class Drawer {

    private Brush brush;

    public Drawer(Brush brush) {
        this.brush = brush;
    }

    public void action() {
        brush.draw();
    }
}
