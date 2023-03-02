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
    float pre_sum;
    float biais = 15;
    ImageView torch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        torch = findViewById(R.id.torch);

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
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                float sum = Math.abs(x) + Math.abs(y) + Math.abs(z);

                if (sum > pre_sum + biais)
                {
                    changeStateFlash();

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cMng.setTorchMode(cMng.getCameraIdList()[0], stateFlash);
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

                pre_sum = sum;
            }
        };
        sensorManager.registerListener(sensorEventListener, sensorShake, SensorManager.SENSOR_DELAY_NORMAL);
    }

    void changeStateFlash(){
        stateFlash = !stateFlash;

        if (stateFlash){
            torch.setImageResource(R.drawable.flashlight_on);
        } else {
            torch.setImageResource(R.drawable.flashlight_off);
        }

    }
}