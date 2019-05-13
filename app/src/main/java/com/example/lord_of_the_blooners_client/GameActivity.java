package com.example.lord_of_the_blooners_client;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GameActivity extends AppCompatActivity {

    private static ConstraintLayout background;
    private static TextView player_score;
    private static TextView player_id;

    public static ConstraintLayout getLayout() {
        return background;
    }
    public static TextView getTextScore() {return player_score; }
    public static TextView getTextId() {return player_id; }

    public void setBackgroundColor(final int color){
        runOnUiThread(new Thread(new Runnable() {
            public void run() {
            GameActivity.getLayout().setBackgroundColor(color);
            Setup.vibrator.vibrate(200);
        }}
        ));
    }

    public void setTextScore(final int score){
        runOnUiThread(new Thread(new Runnable() {
            public void run() {
                GameActivity.getTextScore().setText("Score : " + score);
                GameActivity.getTextId().setText("" +Setup.getMainPlayer().getPlayerID());
            }}
        ));
    }

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


    public static GameActivity game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        game = GameActivity.this;
        GameActivity.background = findViewById(R.id.constraintLayout);
        GameActivity.player_score = findViewById(R.id.player_score);
        GameActivity.player_score.setText("0");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //mForce = findViewById(R.id.textView);
        //mAngle = findViewById(R.id.textView2);

        player_id = findViewById(R.id.player_id);


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
                //mForce.setText("Force : " + strength +"%");
                //mAngle.setText("Angle : " + angle +"°");
                double deltaX = cos(angle/180.0 * 3.14159) * (strength/100.0);
                double deltaY = -sin(angle/180.0 * 3.14159) * (strength/100.0);
                //image = findViewById(R.id.image);
                Setup.getMainPlayer().setDeltaPosition(new Position(deltaX, deltaY));
                //Setup.getMainPlayer().setPosition(image.getX(), image.getY());
            }
        }, 17);

        JoystickView joystick2 = findViewById(R.id.joystickView2);

        joystick2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Setup.getMainPlayer().setEtat(1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Setup.getMainPlayer().setEtat(0);
                }
                return true;
            }
        });
    }
}
