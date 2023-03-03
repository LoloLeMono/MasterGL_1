package com.tp2.exo_6;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorMgr;
    Sensor proxSensor;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imgThing);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        proxSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        Boolean supported = sensorMgr.registerListener((SensorEventListener) this,
                proxSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_PROXIMITY:
                onProximityChanged(event);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void onProximityChanged(SensorEvent event){
        float distance;
        distance = event.values[0];
        ((TextView)findViewById(R.id.prox)).setText(distance + " cm");

        if (distance < 3){
            img.setImageResource(R.drawable.loupe);
        } else if (distance > 3 && distance < 6){
            img.setImageResource(R.drawable.binoculars);
        } else{
            img.setImageResource(R.drawable.telescope);
        }
    }
}