package com.example.loginapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;

public class EditDataActivity extends AppCompatActivity {

    private NumberPicker nEditAge;
    private NumberPicker nEditWeight;
    private NumberPicker nEditHeight;

    private Button nUpdateButton;

    DataBaseHelper dataBaseHelperEdit;

    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

/*        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
//        getSupportActionBar().setLogo(getResources().getDrawable(R.drawable.healthyicon));
//        getSupportActionBar().setDisplayUseLogoEnabled(true);/**/
        //getResources().getDrawable(R.drawable.healthyicon).setBounds(5,0,0,0);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.healthyicon));
        //this.getActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.healthyicon));

        nEditAge = (NumberPicker) findViewById(R.id.edit_age_picker);
        nEditHeight = (NumberPicker) findViewById(R.id.edit_height_picker);
        nEditWeight = (NumberPicker) findViewById(R.id.edit_weight_picker);
        nUpdateButton = (Button) findViewById(R.id.edit_button);

        dataBaseHelperEdit = new DataBaseHelper(this);

        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);
        final String activity = getIntent().getStringExtra("ACTIVITY");

        ArrayList<Object> fetched = dataBaseHelperEdit.getDetails(sessionId);

        nEditAge.setMinValue(1);
        nEditHeight.setMinValue(50);
        nEditWeight.setMinValue(0);

        nEditAge.setMaxValue(120);
        nEditHeight.setMaxValue(500);
        nEditWeight.setMaxValue(500);

        nEditAge.setValue((int) fetched.get(0));
        nEditHeight.setValue((int) fetched.get(1));
        nEditWeight.setValue((int) fetched.get(2));


        nUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = dataBaseHelperEdit.updateUser(sessionId, nEditAge.getValue(), nEditHeight.getValue(), nEditWeight.getValue());

                if (result > 0) {
                    Toast.makeText(EditDataActivity.this,"Updated Successfully", Toast.LENGTH_SHORT).show();
                    if (activity.equals("BMI")) {
                        Intent toBMI = new Intent(EditDataActivity.this, BMICalculationActivity.class);
                        toBMI.putExtra("EXTRA_SESSION_ID", sessionId);
                        startActivity(toBMI);
                    }
                    else {
                        Intent toView = new Intent(EditDataActivity.this, DataActivity.class);
                        toView.putExtra("EXTRA_SESSION_ID", sessionId);
                        startActivity(toView);
                    }

                }

                else {
                    Toast.makeText(EditDataActivity.this,"Update Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
