package com.design_patterns.java._02_structural_patterns._07_bridge._02_after;

import com.design_patterns.java._02_structural_patterns._07_bridge._01_before.Champion;

public class App {
    public static void main(String[] args) {
        Champion kda = new 아리(new KDA());
        kda.skillQ();
        kda.skillW();
    }
}
