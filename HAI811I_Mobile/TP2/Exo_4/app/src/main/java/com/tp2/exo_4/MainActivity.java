package com.tp2.exo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorMgr;
    Sensor dirSensor;
    TextView north, east, west, south;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        dirSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        north = (TextView)findViewById(R.id.north);
        east = (TextView)findViewById(R.id.east);
        west = (TextView)findViewById(R.id.west);
        south = (TextView)findViewById(R.id.south);

        Boolean supported = sensorMgr.registerListener((SensorEventListener) this,
                dirSensor, SensorManager.SENSOR_DELAY_UI);
        sensorMgr.unregisterListener
                ((SensorEventListener) this, dirSensor);
        if (!supported) {
            north.setText("Pas de capteur d'orientation");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorMgr.registerListener((SensorEventListener) this, dirSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        sensorMgr.unregisterListener(this, dirSensor);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x, y;
        if (sensorEvent.sensor.getType() == dirSensor.getType()) {
            x = sensorEvent.values[1];
            y = sensorEvent.values[2];

            if (x < -10) {
                north.setText("HAUT");
                south.setText("");
            } else if (x > 10) {
                south.setText("BAS");
                north.setText("");
            } else{
                north.setText("");
                north.setText("");
            }
            if (y<-20) {
                east.setText("DROITE");
                west.setText("");
            }
            else if (y>20) {
                west.setText("GAUCHE");
                east.setText("");
            }
            else {
                west.setText("");
                east.setText("");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}