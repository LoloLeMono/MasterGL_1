package com.tp1.formulaire;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity
{
    Button returnButton;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //Bundle bundle = getIntent().getExtras();

        String firstName = getIntent().getStringExtra("firstName");
        TextView textViewFirstNameXML = findViewById(R.id.resultText_nom);
        textViewFirstNameXML.setText(firstName);

        String lastName = getIntent().getStringExtra("lastName");
        TextView textView = findViewById(R.id.resultText_prenom);
        textView.setText(lastName);

        String birthday = getIntent().getStringExtra("birthday");
        TextView textViewBirthdayXML = findViewById(R.id.resultText_age);
        textViewBirthdayXML.setText(birthday);

        String competence = getIntent().getStringExtra("skillField");
        TextView textViewCompetenceXML = findViewById(R.id.resultText_competence);
        textViewCompetenceXML.setText(competence);

        String phone = getIntent().getStringExtra("phone");
        TextView textViewPhoneXML = findViewById(R.id.resultText_telephone);
        textViewPhoneXML.setText(phone);

        (findViewById(R.id.button_return)).
                setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Toast.makeText(ResultActivity.this, "Bouton cliqué !", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent (ResultActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

    }
}
