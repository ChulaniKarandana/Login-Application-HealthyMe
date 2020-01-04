package com.example.loginapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;

public class LightSensorService extends Service implements SensorEventListener {


    private SensorManager sensorManager = null;
    private Sensor sensor = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        new SensorEventLoggerTask().execute(event);
//        float value = event.values[0];
//            if ((int) value > 15) {
//                System.out.println("#################################"+ value);
//                Intent intent = new Intent(LightSensorService.this, NotifierLight.class);
//                intent.putExtra("Message", "The Light Intensity is too high for your health");
//                PendingIntent intent1 = PendingIntent.getBroadcast(LightSensorService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), intent1);
//                    System.out.println("timeeeeeeeeeeeeeeee:" + System.currentTimeMillis());
//                }
//            }
//        // stop the sensor and service
//        sensorManager.unregisterListener(this);
//        stopSelf();
    }

    private class SensorEventLoggerTask extends
            AsyncTask<SensorEvent, Void, Void> {
        @Override
        protected Void doInBackground(SensorEvent... events) {
            SensorEvent event = events[0];
            float value = event.values[0];
            if ((int) value > 15) {
                System.out.println("#################################"+ value);
                Intent intent = new Intent(LightSensorService.this,NotifierLight.class);
                intent.putExtra("Message","The Light Intensity is too high for your health");
                PendingIntent intent1 = PendingIntent.getBroadcast(LightSensorService.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),intent1);
                    System.out.println("timeeeeeeeeeeeeeeee:" + System.currentTimeMillis());
                }
            }
            return null;
        }
    }
}
