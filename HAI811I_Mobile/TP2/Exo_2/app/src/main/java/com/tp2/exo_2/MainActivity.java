package com.tp2.exo_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private SensorManager sensorManager;
    private int[] requiredSensors = {Sensor.TYPE_PROXIMITY, Sensor.TYPE_LIGHT, Sensor.TYPE_ACCELEROMETER, Sensor.TYPE_PRESSURE, Sensor.TYPE_HEART_RATE, Sensor.TYPE_HEAD_TRACKER};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(150, 16, 150, 16);

        Button searchButton = new Button(this);
        searchButton.setText("CAPTEURS INDISPONIBLE");
        searchButton.setPadding(20, 16, 20, 0);
        linearLayout.addView(searchButton);
        linearLayout.setGravity(Gravity.CENTER);

        setContentView(linearLayout);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

                for (int sensorType : requiredSensors)
                {
                    Sensor sensor = sensorManager.getDefaultSensor(sensorType);

                    if (sensor == null)
                    {
                        disableFunctionality(sensorType);
                    }
                }
            }
        });

    }

    private void disableFunctionality(int sensorType)
    {
        switch (sensorType)
        {
            case Sensor.TYPE_HEART_RATE:
                Log.d("INDISPONIBLE", "heart_rate");
                break;
            case Sensor.TYPE_PRESSURE:
                Log.d("INDISPONIBLE", "pressure");
                break;
            case Sensor.TYPE_HEAD_TRACKER:
                Log.d("INDISPONIBLE", "head_tracker");
                break;
            default:
                break;
        }

        // Afficher un message d'avertissement à l'utilisateur
        Toast.makeText(this, "Le capteur " + sensorType + " est indisponible sur cet appareil.", Toast.LENGTH_LONG).show();
    }
}