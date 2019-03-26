package com.example.lord_of_the_blooners_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static android.os.AsyncTask.execute;


public class MainActivity extends AppCompatActivity {

    private TextView mForce;
    private TextView mAngle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mForce = findViewById(R.id.textView);
        mAngle = findViewById(R.id.textView2);



        ClientConnexion client=new ClientConnexion();
        client.execute();

        JoystickView joystick = (JoystickView) findViewById(R.id.joystickView);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mForce.setText("Force : " + strength +"%");
                mAngle.setText("Angle : " + angle +"°");
            }
        });
    }
}