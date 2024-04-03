package com.example.materialdesign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout email, login, passw, passw2;
    private ProgressBar pgbar;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        login = findViewById(R.id.login);
        passw = findViewById(R.id.passw);
        passw2 = findViewById(R.id.passw2);
        pgbar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                On récupère les données et on fait les contrôles
                validLogin();
                validEmail();
                validPASSWORD();
                validPASSWORD2();

                if (!validLogin() || !validEmail() || !validPASSWORD() || !validPASSWORD2())
                    return;
//                  Fin du contrôle
                pgbar.setVisibility(View.VISIBLE);
                btn.setVisibility(View.GONE);
            }
        });
    }

    private boolean validLogin() {
        String strLOGIN = login.getEditText().getText().toString().trim().toLowerCase(Locale.ROOT);
        if (strLOGIN.isEmpty()) {
            login.setError("A valid name is required");
            login.requestFocus();
            return false;
        } else {
            login.setError(null);
            return true;
        }
    }

    private boolean validEmail() {
        String strEMAIL = email.getEditText().getText().toString().trim().toLowerCase(Locale.ROOT);
        if (strEMAIL.isEmpty()) {
            email.setError("A valid name is required");
            email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEMAIL).matches()) {
            //contrôle du Pattern class Patterns Email_Address valide
            email.setError(strEMAIL + "A valid e-mail is required (example@example.com)");
            //email.requestFocus();
            return false;
        } else {
            //email.setErrorEnables(false); <- due modi equivalenti
            email.setError(null);
            return true;
        }
    }

    private boolean validPASSWORD() {
        String strPASSWORD = passw.getEditText().getText().toString().trim();
        if (strPASSWORD.isEmpty()) {
            passw.setError("Enter a password, please.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(strPASSWORD).matches()) {
            passw.setError("Please enter a valid password (Example1!)");
            return false;
        } else {
            passw.setError(null);
            passw2.setError(null);
            return true;
        }

    }

    private boolean validPASSWORD2() {
        String strPASSWORD = passw.getEditText().getText().toString().trim();
        String strPASSWORD2 = passw2.getEditText().getText().toString().trim();
        if (strPASSWORD2.isEmpty()) {
            passw2.setError("Please, confirm your password.");
            return false;
        } else if (!strPASSWORD.equals(strPASSWORD2)) {
                passw.setError("Your passwords don't match");
                return false;
        } else {
            passw2.setError(null);
            return true;
        }
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^" + //début de la ligne
            "(?=.*[0-9])" + //au moins un chiffre
            "(?=.*[a-z])" + //au moins une minuscule
            "(?=.*[A-Z])" + //au moins un majuscule
            "(?=.*[@#!$%^&+=])" +   //au moins un caractère spéciale listé
            "(?=\\S+$)" +   //pas d'espace vide
            ".{6,20}" + //minimum 6 caractères et Maxi 20 caractères
            "$");   //fin de ligne
}
