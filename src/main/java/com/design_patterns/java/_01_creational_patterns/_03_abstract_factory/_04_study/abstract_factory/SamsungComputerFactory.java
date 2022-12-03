package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.abstract_factory;

public class SamsungComputerFactory implements ComputerFactory {

    @Override
    public Keyboard createKeyboard() {
        return new SamsungKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new SamsungMouse();
    }
}
