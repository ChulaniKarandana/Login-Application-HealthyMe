package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    private TextView nAge;
    private TextView nHeight;
    private TextView nWeight;

    private Button nEditButton;
    private Button nBackButton;

    DataBaseHelper dataBaseHelperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        nAge = (TextView) findViewById(R.id.data_age);
        nHeight = (TextView) findViewById(R.id.data_height);
        nWeight = (TextView) findViewById(R.id.data_weight);
        nBackButton = (Button) findViewById(R.id.data_back_button);

        nEditButton = (Button) findViewById(R.id.data_edit_button);

        dataBaseHelperData = new DataBaseHelper(this);

        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);

        ArrayList<Object> fetched = dataBaseHelperData.getDetails(sessionId);

        nAge.setText(fetched.get(0).toString());
        nHeight.setText(fetched.get(1).toString());
        nWeight.setText(fetched.get(2).toString());

        nEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toedit = new Intent(DataActivity.this, EditDataActivity.class);
                toedit.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toedit);
            }
        });

        nBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(DataActivity.this, HomeActivity.class);
                toHome.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toHome);
            }
        });

    }
}
