package com.example.lord_of_the_blooners_client;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static android.os.AsyncTask.execute;
import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class MainActivity extends AppCompatActivity {

    private Button connexionButton;
    private EditText nomJoueur;
    private EditText ipServeur;
    private String pseudo;
    private String adresseIP;

    private String blockCharacterSet = "\\~#^|$%&*!,\n\t.";

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Configuration.end = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ipServeur = findViewById(R.id.ip_serveur);
        connexionButton = findViewById(R.id.connexion);
        nomJoueur = findViewById(R.id.nom_joueur);
        nomJoueur.setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(10)});
        connexionButton.setEnabled(false);

        nomJoueur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                connexionButton.setEnabled(s.toString().length() != 0); // dévérouille le boutton si le champ comporte au moins un caractère, le verrouille sinon
                pseudo = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ipServeur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adresseIP = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Setup.vibrator = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);

        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientConnexion client = new ClientConnexion();
                client.execute(pseudo, adresseIP);

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Setup.getConnected()){
                    Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(gameActivity);
                }
            }
        });
    }
}