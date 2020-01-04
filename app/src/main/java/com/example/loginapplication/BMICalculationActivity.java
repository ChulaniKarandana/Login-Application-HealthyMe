package com.example.loginapplication;

import android.content.Intent;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class BMICalculationActivity extends AppCompatActivity {
    Button calculate, update, backButton;
    DataBaseHelper db;
    TextView result,height, weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);
        db = new DataBaseHelper(this);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        calculate = findViewById(R.id.calculate);
        result = findViewById(R.id.result);
        update =findViewById(R.id.update_val);
        backButton = findViewById(R.id.bmi_back_button);


        final int sessionId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);
        setValues(sessionId);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PUpdateIntent = new Intent(BMICalculationActivity.this, EditDataActivity.class);
                PUpdateIntent.putExtra("EXTRA_SESSION_ID", sessionId);
                PUpdateIntent.putExtra("ACTIVITY", "BMI");
                startActivity(PUpdateIntent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(BMICalculationActivity.this, HomeActivity.class);
                toHome.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(toHome);
            }
        });
    }
    private void setValues(int sessionId){
        ArrayList res = db.getDetails(sessionId);
        height.setText((res.get(1)).toString());
        weight.setText((res.get(2)).toString());
    }

    private void calculateBMI(){
        String height_str = height.getText().toString();
        String weight_str = weight.getText().toString();

        if(!"".equals(height_str)  && !"".equals(weight_str)){
            float height_val  = Float.parseFloat(height_str)/100;
            float weight_val = Float.parseFloat(weight_str);

            float bmi = weight_val /(height_val* height_val);

            displayBMI(bmi);
        }
    }
    private  void displayBMI(float bmi){
        String bmi_lbl ="Status : ";

        if(Float.compare(bmi,15f)<=0){
            bmi_lbl = "Very severely underweight";
        }else if (Float.compare(bmi,15f)>0 && Float.compare(bmi,16f)<=0){
            bmi_lbl = "Severely underweight";
        }else if (Float.compare(bmi,16f)>0 && Float.compare(bmi,18.5f)<=0){
            bmi_lbl = "Underweight";
        }else if (Float.compare(bmi,18.5f)>0 && Float.compare(bmi,25f)<=0){
            bmi_lbl = "Normal";
        }else if (Float.compare(bmi,25f)>0 && Float.compare(bmi,35f)<=0){
            bmi_lbl = "Overweight";
        }else if (Float.compare(bmi,35f)>0 && Float.compare(bmi,40f)<=0){
            bmi_lbl = "Severely overweight";
        }else if (Float.compare(bmi,40f)>0){
            bmi_lbl = "Obsessive";
        }
        bmi_lbl = bmi + "\n" + bmi_lbl;
        result.setText(bmi_lbl);

    }

}
