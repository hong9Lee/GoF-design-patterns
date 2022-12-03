package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.factory;

public class KeyboardFactory {

    public Keyboard createKeyboard(String type) {
        Keyboard keyboard = null;

        switch (type) {
            case "LG" :
                keyboard = new LGKeyboard();
                break;
            case  "Samsung" :
                keyboard = new SamsungKeyboard();
                break;
        }

        return keyboard;
    }
}
