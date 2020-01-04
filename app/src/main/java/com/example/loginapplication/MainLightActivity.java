package com.example.loginapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainLightActivity extends AppCompatActivity {

    private Switch light_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_intensity_page);

        light_switch = findViewById(R.id.switch1);

        final AlarmManager scheduler = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainLightActivity.this, LightSensorService.class);
        final PendingIntent scheduledIntent = PendingIntent.getService(getApplicationContext(),
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        light_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    scheduler.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                            10000, scheduledIntent);
                }
                else {
                    scheduler.cancel(scheduledIntent);
                }

            }
        });
    }

}
