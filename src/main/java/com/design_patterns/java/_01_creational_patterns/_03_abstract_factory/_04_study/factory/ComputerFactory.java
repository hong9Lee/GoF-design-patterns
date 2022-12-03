package com.design_patterns.java._01_creational_patterns._03_abstract_factory._04_study.factory;

public class ComputerFactory {

    public void createComputer(String type) {
        KeyboardFactory keyboardFactory = new KeyboardFactory();
        MouseFactory mouseFactory = new MouseFactory();
        /*
        팩토리 메서드 패턴을 사용하여 조건에 따른 객체 생성을 팩토리 클래스로 위임하여 팩토리 클래스에서 객체를 생성하게 된다.
        하지만 구현체를 사용하는 부분의 코드가 길어지게 된다.
         */

        keyboardFactory.createKeyboard(type);
        mouseFactory.createMouse(type);

        System.out.println("-------" + type + " 컴퓨터 완성 ~");
    }
}
