package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button nDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nDataButton = (Button) findViewById(R.id.home_data_button);

        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

        nDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todata = new Intent(HomeActivity.this, DataActivity.class);
                todata.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(todata);
            }
        });
    }

}
