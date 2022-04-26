package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class UDPGameServer{
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private ArrayList<Player> players;
    public UDPGameServer() throws SocketException {
        socket = new DatagramSocket(4445);
        players = new ArrayList<>();
    }

    public void run() throws IOException {
        running = true;

        while (running) {
            buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            addPlayer(packet);
            if(players.size() == 3){
                rollWinner();
            }
        }
        socket.close();
    }

    public void addPlayer(DatagramPacket packet){
        String playerName = new String(packet.getData(), 0, packet.getLength());
        Player player = new Player(playerName, packet.getAddress(), packet.getPort());
        System.out.println(player.toString());
        players.add(player);
    }

    public void rollWinner() throws IOException {
        int pick = ThreadLocalRandom.current().nextInt(0, 2 + 1);
        Player winner = players.get(pick);
        String winnersMessage = "The winner is: " + winner.getName();
        byte[] buffer = winnersMessage.getBytes();

        for(Player player : players){
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, player.getAddress(), player.getPort());
            socket.send(packet);
        }
        socket.close();
    }

    public static void main(String[] args) throws SocketException {
        UDPGameServer gameServer = new UDPGameServer();
        try {
            gameServer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
