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
    private String host = "192.168.1.2";
    private int port = 7778;


    @Override
    protected Void doInBackground(String... params) {
        while(!Configuration.end) {
            String name = params[0];
            if (params[1] != null) {
                host = params[1];
            }
            if (connexion == null) {
                try {
                    InetAddress hostAddress = InetAddress.getByName(host);
                    connexion = new Socket(host, port);
                    writer = new PrintWriter(connexion.getOutputStream(),true);
                    reader = new BufferedInputStream(connexion.getInputStream());
                    command = "0," + name;
                    writer.write(command);
                    writer.flush();
                    Setup.setConnected(true);
                } catch (UnknownHostException e) {
                    connexion=null;
                } catch (IOException e) {
                    connexion=null;
                }
            } else {
                String response;
                try {
                    response = read();
                    if (response != "") {
                    System.out.println("Response : "+response);
                        String tabInfos[] = response.split(",");
                        switch (Integer.parseInt(tabInfos[0])) {
                            case 0:
                                Setup.setMainPlayer(new Player(this, Integer.parseInt(tabInfos[1]), tabInfos[2], tabInfos[3], Integer.parseInt(tabInfos[4]), Integer.parseInt(tabInfos[5])));
                                command = "1," + Setup.getMainPlayer().getDeltaPosition().toString();
                                break;
                            case 1:
                                command = "1," + Setup.getMainPlayer().getDeltaPosition().toString();
                                break;
                            default:
                                break;

                        }
                    System.out.println("Command "+command);
                    writer.write(command);
                    writer.flush();
                    }
                } catch (IOException e) {
                }

                try {
                    Thread.currentThread();
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        int stream = 0;
        byte[] b = new byte[4096];
        stream = reader.read(b);
		if (stream == -1) {
		    return "";
		} else
            response = new String(b, 0, stream);
        return response;
    }
}