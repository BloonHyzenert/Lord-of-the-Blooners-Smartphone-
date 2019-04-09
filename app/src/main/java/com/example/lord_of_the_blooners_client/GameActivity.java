package com.example.lord_of_the_blooners_client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GameActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Déconnexion")
                .setMessage("Etes vous sûr de vouloir vous déconnecter?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent MainActivity = new Intent(GameActivity.this, MainActivity.class);
                        startActivity(MainActivity);
                        Setup.setConnected(false);
                        Configuration.end = true;
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private TextView mForce;
    private TextView mAngle;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mForce = findViewById(R.id.textView);
        mAngle = findViewById(R.id.textView2);


        /*
        Intent intent = getIntent();
        String pseudo = intent.getStringExtra("pseudo");
        String adresseIP = intent.getStringExtra("IP");
        ClientConnexion client = new ClientConnexion();
        client.execute(pseudo, adresseIP);*/

        JoystickView joystick = findViewById(R.id.joystickView);
        joystick.setFixedCenter(false);
        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mForce.setText("Force : " + strength +"%");
                mAngle.setText("Angle : " + angle +"°");
                float deltaX = (float) (cos(angle/180.0 * 3.14159) * (strength/100.0)*6);
                float deltaY = (float) (-sin(angle/180.0 * 3.14159) * (strength/100.0)*6);
                image = findViewById(R.id.image);
                Setup.getMainPlayer().setDeltaPosition(new Position((int)deltaX, (int)deltaY));
                Setup.getMainPlayer().setPosition((int) image.getX(), (int)image.getY());

                image.setY(image.getY() + deltaY);
                image.setX(image.getX() + deltaX);
                /*image.setImageResource(R.drawable.ic_krok);
                par = (ConstraintLayout.LayoutParams)image.getLayoutParams();
                par.editorAbsoluteX += deltaX;
                par.editorAbsoluteY += deltaY;
                System.out.println("par.editorAbsoluteX : " + par.editorAbsoluteX + "   deltaX : " + deltaX + "\npar.editorAbsoluteY : " + par.editorAbsoluteY + "  deltaY : " + deltaY);
                image.setLayoutParams(par);*/
            }
        }, 17);
    }
}
