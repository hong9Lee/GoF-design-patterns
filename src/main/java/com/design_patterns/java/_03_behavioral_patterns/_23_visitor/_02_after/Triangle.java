package com.design_patterns.java._03_behavioral_patterns._23_visitor._02_after;

public class Triangle implements Shape {


    @Override
    public void accept(Device device) {
        device.print(this);
    }
}
