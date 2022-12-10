package com.design_patterns.java._02_structural_patterns._07_bridge._04_study;

public class App {

    public static void main(String[] args) {
        Brush redBrush = new HBPencil(new Red());
        redBrush.draw();
    }
}
