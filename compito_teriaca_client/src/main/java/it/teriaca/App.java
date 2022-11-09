package it.teriaca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class App {
    private static String Q = "Q";

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 3000);
        ServerConnection serverConn = new ServerConnection(s);

        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Client connesso");
        System.out.println(br.readLine());

        new Thread(serverConn).start();

        for (;;) {
            String x = tastiera.readLine();
            pr.println(x);
            if (x.equals(Q)) {
                break;
            }
        }
        System.exit(0);
    }
    
}
