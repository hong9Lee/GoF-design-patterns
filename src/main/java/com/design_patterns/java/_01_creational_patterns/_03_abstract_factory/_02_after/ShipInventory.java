package com.design_patterns.java._01_creational_patterns._03_abstract_factory._02_after;


public class ShipInventory {

    public static void main(String[] args) {
        ShipFactory shipFactory = new WhiteShipFactory(new WhitePartsProFactory());
        Ship ship = shipFactory.createShip();
        
    }
}
