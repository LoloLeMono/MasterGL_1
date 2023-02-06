package com.tp1.formulaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button)).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Bouton cliqué !", Toast.LENGTH_LONG).show();
                    }
                });

        /*
        // EXO 3 : DANS LA LOGIQUE METIER
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(16, 16, 16, 16);

        LinearLayout nameLayout = new LinearLayout(this);
        nameLayout.setOrientation(LinearLayout.HORIZONTAL);
        nameLayout.setPadding(0, 16, 0, 0);

        TextView nameLabel = new TextView(this);
        nameLabel.setText("Nom :");
        nameLayout.addView(nameLabel);

        EditText nameEditText = new EditText(this);
        nameEditText.setHint("Entrer votre nom");
        nameLayout.addView(nameEditText);

        linearLayout.addView(nameLayout);

        LinearLayout firstNameLayout = new LinearLayout(this);
        firstNameLayout.setOrientation(LinearLayout.HORIZONTAL);
        firstNameLayout.setPadding(0, 16, 0, 0);
        TextView firstNameLabel = new TextView(this);
        firstNameLabel.setText("Prénom :");
        firstNameLayout.addView(firstNameLabel);

        EditText firstNameEditText = new EditText(this);
        firstNameEditText.setHint("Entrer votre prénom");
        firstNameLayout.addView(firstNameEditText);

        linearLayout.addView(firstNameLayout);

        LinearLayout ageLayout = new LinearLayout(this);
        ageLayout.setOrientation(LinearLayout.HORIZONTAL);
        ageLayout.setPadding(0, 16, 0, 0);

        TextView ageLabel = new TextView(this);
        ageLabel.setText("Age :");
        ageLayout.addView(ageLabel);

        EditText ageEditText = new EditText(this);
        ageEditText.setHint("Entrer votre âge");
        ageLayout.addView(ageEditText);

        linearLayout.addView(ageLayout);

        LinearLayout domainLayout = new LinearLayout(this);
        domainLayout.setOrientation(LinearLayout.HORIZONTAL);
        domainLayout.setPadding(0, 16, 0, 0);

        TextView domainLabel = new TextView(this);
        domainLabel.setText("Domaine de compétences :");
        domainLayout.addView(domainLabel);

        EditText domainEditText = new EditText(this);
        domainEditText.setHint("Entrer votre domaine de compétences");
        domainLayout.addView(domainEditText);

        linearLayout.addView(domainLayout);

        LinearLayout phoneLayout = new LinearLayout(this);
        phoneLayout.setOrientation(LinearLayout.HORIZONTAL);
        phoneLayout.setPadding(0, 16, 0, 0);

        TextView phoneLabel = new TextView(this);
        phoneLabel.setText("Numéro de téléphone :");
        phoneLayout.addView(phoneLabel);

        EditText phoneEditText = new EditText(this);
        phoneEditText.setHint("Entrer votre numéro de téléphone");
        phoneLayout.addView(phoneEditText);

        linearLayout.addView(phoneLayout);

        Button sendButton = new Button(this);
        sendButton.setText("Envoyer");
        sendButton.setPadding(0, 16, 0, 0);
        linearLayout.addView(sendButton);

        setContentView(linearLayout);
        */
    }
}