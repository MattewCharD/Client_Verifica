package it.ciardi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        Scanner sc = new Scanner(System.in);
        
        Socket s = new Socket("localhost",3000);
        System.out.println("Connessione effettuata. Digita ESCI per uscire.  \n" );
        
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String message;

        do {
            message = sc.nextLine();
            
            out.writeBytes( message + "\n");
            String stringaRicevuta = in.readLine();
            if (message.equals("!") ) {
                break;
            }
            String stringaMaiuscola = stringaRicevuta.toUpperCase();
            System.out.println("La stringa restituita è: " + stringaMaiuscola);

            
        } while (!message.equals("!") );
        s.close();
        
    }
}