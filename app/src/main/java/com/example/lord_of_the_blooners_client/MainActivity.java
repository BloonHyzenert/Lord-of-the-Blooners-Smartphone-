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


        Thread t = new Thread(new ClientConnexion());
        t.start();

        mFeedback.setText("BLOOOOOOOOOOOOOOOOOOOOOOON!");
    }
}