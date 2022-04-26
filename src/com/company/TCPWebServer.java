package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPWebServer {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) return;

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(output, true);

                String[] request = input.readLine().split("/");
                String[] page = request[1].split(" ");
                System.out.println(page[0]);

                if(page[0].equals("openpage")){
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-type: text/html");
                    writer.println("\r\n");

                    writer.println("<h1> Page is open!! </h1>");
                }
                else{
                    writer.println("HTTP/1.1 404 Not Found");
                    writer.println("Content-type: text/html");
                    writer.println("\r\n");

                    writer.println("<h1> Page does not exist open!! </h1>");
                }


            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
