package com.example.lord_of_the_blooners_client;

import java.io.BufferedInputStream;

import java.io.IOException;

import java.io.PrintWriter;

import java.net.Socket;

import java.net.UnknownHostException;

public class ClientConnexion implements Runnable{


    private Socket connexion = null;

    private PrintWriter writer = null;

    private BufferedInputStream reader = null;

    private String command ;

    private String name = "Bloon";

    private Player player;

    private String host = "10.0.2.2";

    private int port = 7777;

    public ClientConnexion(){
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

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