package com.tp2.exo_5;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean stateFlash = false;
    float pre_x, pre_y, pre_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView torch = findViewById(R.id.torch);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        CameraManager cMng = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                switch (sensorEvent.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        detectShake(sensorEvent);
                        break;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public void detectShake(SensorEvent sensorEvent){
                float x_accl = sensorEvent.values[0];
                float y_accl = sensorEvent.values[1];
                float z_accl = sensorEvent.values[2];

                float floatSum = Math.abs(x_accl) + Math.abs(y_accl) + Math.abs(z_accl);

                if (floatSum > 14)
                {
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            stateFlash = !stateFlash;
                            cMng.setTorchMode(cMng.getCameraIdList()[1], true);
                        }
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (stateFlash){
                    torch.setImageResource(R.drawable.flashlight_on);
                }
                else {
                    torch.setImageResource(R.drawable.flashlight_off);
                }

                pre_x = x_accl;
                pre_y = y_accl;
                pre_z = z_accl;
            }
        };
        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
    }
}