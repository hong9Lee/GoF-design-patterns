package com.design_patterns.java._03_behavioral_patterns._19_observer._02_after;

public class Client {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("keesun");
        User user2 = new User("hong");

        chatServer.register("game1", user1);
        chatServer.register("game2", user2);

        chatServer.register("game10", user1);

        chatServer.sendMessage(user1, "game1", "gameover");
        chatServer.sendMessage(user2, "game2", "gameover~");


    }
}
