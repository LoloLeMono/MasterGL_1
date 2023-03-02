package com.tp2.exo_4;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    ImageView arrow;
    float x_prev, y_prev, z_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrow = findViewById(R.id.arrow);
        TextView pos = findViewById(R.id.pos);

        float biais = (float)0.7;

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

                pos.setText("x : " + x + ", y : " + y);

                // x positif = gauche
                // x negatif = droite
                // y positif = haut
                // y negatif = bas

                if (x > x_prev+biais) {
                    arrow.setImageResource(R.drawable.arrow_left);
                } else if (x < x_prev-biais){
                    arrow.setImageResource(R.drawable.arrow_right);
                } else if (y > y_prev+biais) {
                    arrow.setImageResource(R.drawable.arrow_top);
                } else if (y < y_prev-biais) {
                    arrow.setImageResource(R.drawable.arrow_bottom);
                }

                /*
                if (y + 0.2 > y_prev) {
                    arrow.setImageResource(R.drawable.arrow_top);
                } else if (y + 0.2 < y_prev) {
                    arrow.setImageResource(R.drawable.arrow_bottom);
                }

                 */

                x_prev = x;
                y_prev = y;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Ne fait rien
            }
        }, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
}