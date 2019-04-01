package com.example.lord_of_the_blooners_client;

import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsoluteLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static android.os.AsyncTask.execute;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class MainActivity extends AppCompatActivity {

    private TextView mForce;
    private TextView mAngle;
    private ImageView image;

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
                float deltaX = (float) (cos(angle/180.0 * 3.14159) * (strength/100.0)*30);
                float deltaY = (float) (-sin(angle/180.0 * 3.14159) * (strength/100.0)*30);

                image = findViewById(R.id.image);
                image.setY(image.getY() + deltaY);
                image.setX(image.getX() + deltaX);
                /*image.setImageResource(R.drawable.ic_krok);
                par = (ConstraintLayout.LayoutParams)image.getLayoutParams();
                par.editorAbsoluteX += deltaX;
                par.editorAbsoluteY += deltaY;
                System.out.println("par.editorAbsoluteX : " + par.editorAbsoluteX + "   deltaX : " + deltaX + "\npar.editorAbsoluteY : " + par.editorAbsoluteY + "  deltaY : " + deltaY);
                image.setLayoutParams(par);*/
            }
        });
    }
}