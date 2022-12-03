package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.factory;

public class Client {
    public static void main(String[] args) {
        ComputerFactory computerFactory = new ComputerFactory();
        computerFactory.createComputer("LG");
    }
}
