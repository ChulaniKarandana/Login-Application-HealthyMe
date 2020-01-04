package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button nDataButton;
    private Button nBMIButton;
    private Button nReminderButton;
    private Button nLightButton;
    private Button nStepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nDataButton = (Button) findViewById(R.id.home_data_button);
        nBMIButton = (Button) findViewById(R.id.home_bmi_button);
        nReminderButton = (Button) findViewById(R.id.home_reminder_button);
        nLightButton = (Button) findViewById(R.id.home_light_button);
        nStepButton = (Button) findViewById(R.id.home_step_button);

        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

        nDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todata = new Intent(HomeActivity.this, DataActivity.class);
                todata.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(todata);
            }
        });

        nBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toBMI = new Intent(HomeActivity.this, BMICalculationActivity.class);
                toBMI.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toBMI);
            }
        });

        nReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toReminder = new Intent(HomeActivity.this, MainReminderActivity.class);
                toReminder.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toReminder);
            }
        });

        nLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLight = new Intent(HomeActivity.this, MainLightActivity.class);
                toLight.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toLight);
            }
        });

        nStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toStep = new Intent(HomeActivity.this, StepCounter.class);
                toStep.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toStep);
            }
        });
    }

}
