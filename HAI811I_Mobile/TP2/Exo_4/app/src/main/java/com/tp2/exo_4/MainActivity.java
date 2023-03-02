package com.tp2.exo_4;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorMgr;
    Sensor dirSensor;
    ImageView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrow = findViewById(R.id.arrow);

        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        dirSensor = sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Boolean supported = sensorMgr.registerListener((SensorEventListener) this,
                dirSensor, SensorManager.SENSOR_DELAY_UI);
        sensorMgr.unregisterListener
                ((SensorEventListener) this, dirSensor);
        if (!supported) {
            arrow.setVisibility(View.INVISIBLE);
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

    private static final float NS2S = 1.0f / 1000000000.0f; // Convert nanoseconds to seconds
    private static final float EPSILON = 0.000001f; // Epsilon value to avoid division by zero
    private long previousTimestamp;
    private float[] previousPosition = new float[3];
    private float[] currentVelocity = new float[3];
    private float[] currentAcceleration = new float[3];
    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];
    private float[] gravity = new float[3];

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                System.arraycopy(event.values, 0, currentAcceleration, 0, 3);
                break;
            case Sensor.TYPE_GRAVITY:
                System.arraycopy(event.values, 0, gravity, 0, 3);
                break;
            default:
                return;
        }

        float dt = (event.timestamp - previousTimestamp) * NS2S;
        previousTimestamp = event.timestamp;

        // Remove gravity from the acceleration values
        float alpha = 0.8f;
        float[] smoothedAcceleration = new float[3];
        smoothedAcceleration[0] = alpha * currentAcceleration[0] + (1 - alpha) * gravity[0];
        smoothedAcceleration[1] = alpha * currentAcceleration[1] + (1 - alpha) * gravity[1];
        smoothedAcceleration[2] = alpha * currentAcceleration[2] + (1 - alpha) * gravity[2];

        // Convert the smoothed acceleration to the device coordinate system
        SensorManager.getRotationMatrix(rotationMatrix, null, gravity, event.values);
        SensorManager.getOrientation(rotationMatrix, orientation);
        float[] deviceAcceleration = new float[3];
        deviceAcceleration[0] = smoothedAcceleration[0] * (float)Math.cos(orientation[1]) * (float)Math.cos(orientation[2]);
        deviceAcceleration[1] = smoothedAcceleration[1] * (float)Math.sin(orientation[2]);
        deviceAcceleration[2] = smoothedAcceleration[2] * (float)Math.sin(orientation[1]) * (float)Math.cos(orientation[2]);

        // Integrate the acceleration to get the velocity
        for (int i = 0; i < 3; i++) {
            currentVelocity[i] += (currentAcceleration[i] + deviceAcceleration[i]) / 2.0f * dt;
        }

        // Integrate the velocity to get the position
        for (int i = 0; i < 3; i++) {
            if (i == 0){
                if (previousPosition[0] + currentVelocity[0] < previousPosition[0]){
                    arrow.setImageResource(R.drawable.arrow_left);
                } else if (previousPosition[0] + currentVelocity[0] > previousPosition[0]) {
                    arrow.setImageResource(R.drawable.arrow_right);
                }
            } else if (i == 1) {
                if (currentVelocity[1] < 0) {
                    //arrow.setImageResource(R.drawable.arrow_left);
                } else if (currentVelocity[1] < 0) {
                    //arrow.setImageResource(R.drawable.arrow_left);
                }
            }
            previousPosition[i] += currentVelocity[i] * dt;
        }

        // Do something with the position, such as update a UI element
        Log.d("MySensorEventListener", "xPosition = " + previousPosition[0] + ", yPosition = " + previousPosition[1] + ", zPosition = " + previousPosition[2]);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}