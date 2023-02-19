package com.tp1.formulaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_call extends AppCompatActivity
{

    private static final String PHONE_NUMBER = "0123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        ImageView imageView = findViewById(R.id.image_view);
        TextView phoneTextView = findViewById(R.id.phone_text_view);
        Button callButton = findViewById(R.id.call_button);

        // Set the image
        // imageView.setImageResource(R.drawable.image_view);


        // Set the phone number
        //phoneTextView.setText(PHONE_NUMBER);
        phoneTextView.setText(getIntent().getStringExtra("phone"));

        // Set the click listener for the call button
        callButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getIntent().getStringExtra("phone")));
                startActivity(intent);
            }
        });
    }
}