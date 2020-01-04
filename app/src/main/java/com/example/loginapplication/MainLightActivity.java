package com.example.loginapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainLightActivity extends AppCompatActivity {

    private Switch light_switch;
    private Button light_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_intensity_page);

        light_switch = findViewById(R.id.switch1);
        light_back = findViewById(R.id.light_back);

        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

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

        light_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(MainLightActivity.this, HomeActivity.class);
                toHome.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toHome);
            }
        });
    }

}
