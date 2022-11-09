package it.teriaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket s;

    public Socket getS() {
        return s;
    }

    private PrintWriter pr = null;
    private BufferedReader br = null;
    private int c;
    private String comando;
    static int ticket = 8;
    static String nomeServer = "Server_Teriaca";
    private List<ClientHandler> clients;

    // contatore = contatore+1;
    public ClientHandler(Socket s, int c, List<ClientHandler> x) {
        this.clients = x;
        this.s = s;
        this.comando = "";
        this.c = c;
        try {
            pr = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            System.out.println(br.readLine());
            pr.println("Server: Sei l'utente n. " + c  + " non farti scappare i biglietti!");
            
            for (;;) {
                comando = br.readLine();

                if(ticket == 0){
                    sendToAll("I bilglietti sono TERMINATI");
                    sendToAll("@");
                    System.out.println("Tutte le connessioni sono state chiuse!");
                    clients.removeAll(clients);
                    break;
                }

                else if ((comando.equals("D"))||comando.equals("d")){
                    pr.println("I biglietti attualmente disponibili sono: " + ticket);

                } else if ((comando.equals("A"))||comando.equals("a")) {
                    if(ticket > 0){
                        pr.println("Hai appena acquistato un biglietto, GRAZIE PER AVERCI SCELTO");
                        ticket--;
                    }else{
                        pr.println("Ci dispiace ma i biglietti sono attualmente finiti!");
                    }

                } else if ((comando.equals("Q"))||comando.equals("q")) {
                    pr.println("Chiusura Connessione");

                    System.out.println("Connessione chiusa con utente n. " + c);
                    s.close();
                    break;

                } else {
                    pr.println("Il comando inserito non è valido");

                }
            }
        } catch (Exception e) {

        }
    }
    private void sendToAll(String msg) {
        for (ClientHandler client : clients) {
            client.pr.println(msg);
        }
    }
}
