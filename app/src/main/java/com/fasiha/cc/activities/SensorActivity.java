package com.fasiha.cc.activities;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasiha.cc.R;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    Button show;
    TextView showHeartRate;
    SensorManager sensorMgr;
    Sensor heartRate;
    String heartRateValue;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        heartRateValue = Integer.toString(sensorEvent.sensor.getType());
        showHeartRate.setText(heartRateValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);
        show = (Button) findViewById(R.id.button);
        show.setOnClickListener(displayHeartRate);
        showHeartRate = (TextView) findViewById(R.id.showHeartRate);
        sensorMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        heartRate = sensorMgr.getDefaultSensor(Sensor.TYPE_HEART_RATE);

    }

    View.OnClickListener displayHeartRate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showHeartRate.setText(heartRateValue);

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        sensorMgr.registerListener(this, heartRate, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);
    }
}
