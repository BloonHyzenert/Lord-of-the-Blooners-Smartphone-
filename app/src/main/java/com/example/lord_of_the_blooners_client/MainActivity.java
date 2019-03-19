package com.example.lord_of_the_blooners_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class MainActivity extends AppCompatActivity {

    private TextView mFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFeedback = findViewById(R.id.textView);

        System.out.println("Test 1024 ports :");
        for(int i = 1; i <= 6000; i++){
            try {
                System.out.println("Essai port num " + i);
                Socket soc = new Socket("127.0.0.1", i);
                System.out.println("La machine autorise les connexions sur le port : " + i);
            } catch (UnknownHostException e) {
                System.out.println("CRAAAAASH");
                System.out.println("CRAAAAASH");
                System.out.println("CRAAAAASH");
                System.out.println("CRAAAAASH");
                e.printStackTrace();
            }catch (IOException e) {
                //Si une exception de ce type est levée
                //c'est que le port n'est pas ouvert ou n'est pas autorisé
            }
        }
    /*
        String host = "161.3.23.11";
        int port = 135;

        for(int i = 0; i < 5; i++){
            Thread t = new Thread(new ClientConnexion(host, port));
            t.start();
        }
*/
        mFeedback.setText("BLOOOOOOOOOOOOOOOOOOOOOOON!");
    }
}