package com.example.tom.sensorquery;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView mTextView;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mTextView = (TextView) findViewById(R.id.thebigvu);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if ((mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) != null){
            // Success!
            mTextView.setText("accelerometer found");
            mSensorManager.registerListener(this, mSensor , SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            // Failure! No magnetometer.
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        String str = "";
        float lux = event.values[0];
        str += event.values[0];
        str += "\n";
        str += event.values[1];
        str += "\n";
        str += event.values[2];
        mTextView.setText(str);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
