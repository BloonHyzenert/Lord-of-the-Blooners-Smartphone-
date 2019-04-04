package com.example.lord_of_the_blooners_client;

import android.os.AsyncTask;

import java.io.BufferedInputStream;

import java.io.IOException;

import java.io.PrintWriter;

import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;

public class ClientConnexion extends AsyncTask<String,Void,Void> {


    private Socket connexion = null;

    private PrintWriter writer = null;

    private BufferedInputStream reader = null;

    private String command ;

    private String host = "192.168.43.144";

    private int port = 7778;


    @Override
    protected Void doInBackground(String... params) {
        String name = params[0];
        if (params[1] != null) {
            host = params[1];
        }
        if(connexion==null) {
            try {
                InetAddress hostAddress = InetAddress.getByName(host);
                connexion = new Socket(host, port);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(connexion!=null) {

            try {
                writer = new PrintWriter(connexion.getOutputStream(), true);
                reader = new BufferedInputStream(connexion.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        while (connexion!=null) {
            if(command==null)
                command="0,"+ name;

            writer.write(command);
            writer.flush();

            System.out.println("Commande " + command + " envoyée au serveur");
            //On attend la réponse
            String response;
            try {
                response = read();
                System.out.println("\t * " + name + " : Réponse reçue " + response);

                String tabInfos[] = response.split(",");
                if (Setup.getMainPlayer().getPlayerID()==0) {
                    Setup.setMainPlayer(new Player(this,Integer.parseInt(tabInfos[0]),tabInfos[1],tabInfos[2],Integer.parseInt(tabInfos[3]), Integer.parseInt(tabInfos[4])));
                    command= Setup.getMainPlayer().getPlayerID() +","+ Setup.getMainPlayer().getDeltaPosition().toString();
                }
                else if(Setup.getMainPlayer().getPlayerID()==Integer.parseInt(tabInfos[0]))
                    command = Setup.getMainPlayer().getPlayerID() +","+ Setup.getMainPlayer().getDeltaPosition().toString();

                else command="Erreur";

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

            try {
                Thread.currentThread();
                Thread.sleep(20);
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