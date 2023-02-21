package com.tp2.exo_3;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout ln = new LinearLayout(this);
        ln.setOrientation(LinearLayout.VERTICAL);
        ln.setBackgroundColor(Color.BLACK);

        // Obtention du gestionnaire de capteurs
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Obtention de l'accéléromètre
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Enregistrement de l'écouteur de capteurs
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                Log.d("Accéléromètre", "x = " + x + ", y = " + y + ", z = " + z);

                if (x < 3)
                {
                    ln.setBackgroundColor(Color.GREEN);
                } else if (x > 6)
                {
                    ln.setBackgroundColor(Color.RED);
                } else
                {
                    ln.setBackgroundColor(Color.BLACK);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Ne fait rien
            }
        }, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        setContentView(ln);

    }
}