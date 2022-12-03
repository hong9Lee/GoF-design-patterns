package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.abstract_factory;

public class Client {
    public static void main(String[] args) {
        FactoryOfComputerFactory fac = new FactoryOfComputerFactory();
        fac.createComputer("LG");
    }
}
