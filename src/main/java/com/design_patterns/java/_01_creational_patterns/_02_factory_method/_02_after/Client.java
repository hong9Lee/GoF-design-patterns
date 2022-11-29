package com.design_patterns.java._01_creational_patterns._02_factory_method._02_after;

public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteShipFactory(), "whiteship", "lee@email.com");
        client.print(new BlackShipFactory(), "whiteship", "lee@email.com");


        Ship whiteship = new WhiteShipFactory()
                .orderShip("WhiteShip", "hong@email.com");
        System.out.println(whiteship);

        Ship blackship = new BlackShipFactory()
                .orderShip("Blackship", "keesun@mail.com");

        System.out.println(blackship);
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }

}
