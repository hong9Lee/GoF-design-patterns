package com.design_patterns.java._01_creational_patterns._03_abstract_factory._03_java;

import com.design_patterns.java._01_creational_patterns._03_abstract_factory._02_after.Ship;
import com.design_patterns.java._01_creational_patterns._03_abstract_factory._02_after.WhiteShip;
import org.springframework.beans.factory.FactoryBean;

public class ShipFactory implements FactoryBean<Ship> {

    @Override
    public Ship getObject() throws Exception {
        Ship ship = new WhiteShip();
        ship.setName("whiteship");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return Ship.class;
    }
}
