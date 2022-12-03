package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.abstract_factory;

public class FactoryOfComputerFactory {
    public void createComputer(String type) {
        ComputerFactory computerFactory = null;

        switch (type) {
            case "LG" :
                computerFactory = new LGComputerFactory();
                break;

            case "Samsung" :
                computerFactory = new SamsungComputerFactory();
                break;
        }

        computerFactory.createKeyboard();
        computerFactory.createMouse();


    }

}
