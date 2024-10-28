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

        int porta = 3000; // porta
        Scanner sc = new Scanner(System.in);

        Socket s = new Socket("localhost", porta);
        System.out.println("Connessione effettuata. Digita ESCI per uscire.  \n");
        System.out.println("Inserisci la nota da memorizzare o digita LISTA per visualizzare le note salvate.\n");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String message;
        String stringaRicevuta;

        do {
            message = sc.nextLine();

            switch (message) {
                case "LISTA":
                    message = "?";
                    out.writeBytes(message + "\n");
                    System.out.println("Ecco la tua lista: \n");
                    do {
                        stringaRicevuta = in.readLine();
                        if (stringaRicevuta.equals("@")) {
                            break;
                        } else { 
                            System.out.println(stringaRicevuta);
                        }
                    } while (!stringaRicevuta.equals("@"));
                    System.out.println("Adesso puoi continuare ad inserire altre note");
                    break;

                case "ESCI":
                    message = "!";
                    out.writeBytes(message + "\n");
                    System.out.println("Disconnessione effettuata ");
                    break;

                default:
                    out.writeBytes(message + "\n");
                    stringaRicevuta = in.readLine();
                    
                    if (stringaRicevuta.equals("OK")) {
                        System.out.println("Nota salvata");
                    } else{
                        System.out.println("Errore");
                    }
                    break;
            }


        } while (!message.equals("!"));
        s.close();

    }
}