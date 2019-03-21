package com.example.lord_of_the_blooners_client;

import android.os.AsyncTask;

import java.io.BufferedInputStream;

import java.io.IOException;

import java.io.PrintWriter;

import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;

public class ClientConnexion extends AsyncTask<Void,Void,Void> {


    private Socket connexion = null;

    private PrintWriter writer = null;

    private BufferedInputStream reader = null;

    private String command ;

    private Player player;

    private String host = "192.168.1.2";

    private int port = 7777;



    @Override
    protected Void doInBackground(Void... voids) {
        String name = "Bloon";


        if(connexion==null) {
            try {
                InetAddress hostAddress = InetAddress.getByName(host);
                connexion = new Socket(hostAddress, port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            writer = new PrintWriter(connexion.getOutputStream(), true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            reader = new BufferedInputStream(connexion.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (connexion!=null) {
            if(command==null)
                command="0,Bloon";

            writer.write(command);
            writer.flush();

            System.out.println("Commande " + command + " envoyée au serveur");
            //On attend la réponse
            String response;
            try {
                response = read();
                System.out.println("\t * " + name + " : Réponse reçue " + response);

                String tabInfos[] = response.split(",");
                if (player==null) {
                    player=new Player(this,Integer.parseInt(tabInfos[0]),tabInfos[1],tabInfos[2]);
                    command= player.getPlayerID() +","+ player.getPosition().toString();
                }
                else if(player.getPlayerID()==Integer.parseInt(tabInfos[0]))
                    command = player.getPlayerID() +","+ player.getPosition().toString();

                else command="Erreur";

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            try {
                Thread.currentThread();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }


    //Méthode pour lire les réponses du serveur
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }
}