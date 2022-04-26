package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        if (args.length < 2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        Scanner sc= new Scanner(System.in);

        try (Socket socket = new Socket(hostname, port)) {
            while(true){
                InputStream input = socket.getInputStream();
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();
                System.out.println(time);

                String str= sc.nextLine();
                System.out.println(str);
                output.println("Stan:" + str);

            }
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
