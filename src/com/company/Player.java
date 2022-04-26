package com.company;

import java.net.InetAddress;

public class Player {
    private String name;
    private InetAddress address;
    private int port;

    public Player(String name, InetAddress address, int port){
        this.address = address;
        this.port = port;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
