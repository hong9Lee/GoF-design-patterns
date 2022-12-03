package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.abstract_factory;

public class LGComputerFactory implements ComputerFactory {

    @Override
    public Keyboard createKeyboard() {
        return new LGKeyboard();
    }

    @Override
    public Mouse createMouse() {
        return new LGMouse();
    }
}
