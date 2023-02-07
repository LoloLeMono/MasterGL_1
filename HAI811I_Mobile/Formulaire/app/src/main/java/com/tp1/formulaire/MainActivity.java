package com.tp1.formulaire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button sendButton;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.button);
        builder = new AlertDialog.Builder(this);
        sendButton.setOnClickListener(v -> {
            builder.setTitle(R.string.dialog_title)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            //dialog.cancel();
                            String firstName = ( (EditText) findViewById(R.id.editText_prenom)).getText().toString();
                            String lastName = ( (EditText) findViewById(R.id.editText_nom)).getText().toString();
                            String birthday = ( (EditText) findViewById(R.id.editText_age)).getText().toString();
                            String skillField = ( (EditText) findViewById(R.id.editText_competences)).getText().toString();
                            String phone = ( (EditText) findViewById(R.id.editText_telephone)).getText().toString();

                            Intent intent = new Intent (MainActivity.this, ResultActivity.class);
                            Bundle bundle = new Bundle();

                            bundle.putString("firstName", firstName);
                            bundle.putString("lastName", lastName);
                            bundle.putString("birthday", birthday);
                            bundle.putString("skillField", skillField);
                            bundle.putString("phone", phone);

                            intent.putExtras(bundle);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(),"Vous avez choisi oui",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Vous avez choisi non",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

            //Creating dialog box
            AlertDialog alert = builder.create();
            alert.show();

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