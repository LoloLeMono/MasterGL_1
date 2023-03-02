package com.tp2.exo_3;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = findViewById(R.id.ll);
        TextView infos = findViewById(R.id.pos);
        TextView infosSum = findViewById(R.id.somme);

        // Obtention du gestionnaire de capteurs
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtention de l'accéléromètre
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Enregistrement de l'écouteur de capteurs
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = Math.abs(event.values[0]);
                float y = Math.abs(event.values[1]);
                float z = Math.abs(event.values[2]);

                float sum = x+y+z;

                infos.setText("x : " + x + " y : " + y + ", z : " + z);
                infosSum.setText("Somme : " + sum);

                if (sum > 13 && sum < 16)
                {
                    ll.setBackgroundColor(Color.GREEN);
                } else if (sum > 16) {
                    ll.setBackgroundColor(Color.RED);
                } else
                {
                    ll.setBackgroundColor(Color.BLACK);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Ne fait rien
            }
        }, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
}