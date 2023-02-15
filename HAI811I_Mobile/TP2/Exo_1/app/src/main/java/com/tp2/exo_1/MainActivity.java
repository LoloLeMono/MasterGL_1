package com.tp2.exo_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        final SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer sensorDesc = new StringBuffer();

        // Créez un ScrollView
        ScrollView scrollView = new ScrollView(this);

        // Créez un LinearLayout qui contiendra vos TextView
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Ajoutez vos TextView au LinearLayout
        TextView textView = new TextView(this);

        for (Sensor sensor : sensors)
        {
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append("\tName: " + sensor.getName() + "\r\n");
            sensorDesc.append("\tType: " + sensor.getType() + "\r\n");
            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution (in the sensor unit): " +
                    sensor.getResolution() + "\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use" +
                    sensor.getPower() +"\r\n");
            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." +
                    sensor.getMaximumRange() + "\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond" + "or zero if this sensor only returns a value when the data it's measuring changes" +
                    sensor.getMinDelay() + "\r\n");

        }

        //Log.d(sensorDesc.toString());
        textView.setText(sensorDesc.toString());
        linearLayout.addView(textView);

        // Ajoutez le LinearLayout au ScrollView
        scrollView.addView(linearLayout);

        // Définissez le ScrollView comme le contenu de votre activité
        setContentView(scrollView);
    }
}