package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.factory;

public class MouseFactory {

    public Mouse createMouse(String type) {
        Mouse mouse = null;

        switch (type) {
            case "LG" :
                mouse = new LGMouse();
                break;
            case  "Samsung" :
                mouse = new SamsungMouse();
                break;
        }

        return mouse;
    }
}
