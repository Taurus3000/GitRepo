package ru.gb.jtwo.lesson_four.online.server.core;

public class ChatServer {
    public void start(int port) {
        System.out.println("Server started at port: " + port);
    }

    public void stop() {
        System.out.println("Server stopped");
    }
}
