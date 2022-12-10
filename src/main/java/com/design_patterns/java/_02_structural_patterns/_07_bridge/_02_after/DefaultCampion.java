package com.design_patterns.java._02_structural_patterns._07_bridge._02_after;

import com.design_patterns.java._02_structural_patterns._07_bridge._01_before.Champion;

public class DefaultCampion implements Champion {

    private Skin skin;
    private String name;

    public DefaultCampion(Skin skin, String name) {
        this.skin = skin;
        this.name = name;
    }

    @Override
    public void move() {
        System.out.printf("%s %s move\n", skin.getName(), this.name);
    }

    @Override
    public void skillQ() {
        System.out.printf("%s %s skill Q\n", skin.getName(), this.name);
    }

    @Override
    public void skillW() {
        System.out.printf("%s %s skill W\n", skin.getName(), this.name);
    }

    @Override
    public void skillE() {
        System.out.printf("%s %s skill E\n", skin.getName(), this.name);
    }

    @Override
    public void skillR() {
        System.out.printf("%s %s skill R\n", skin.getName(), this.name);
    }
}
