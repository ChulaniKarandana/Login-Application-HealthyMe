package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText nTextUsername;
    private EditText nTextPassword;
    private Button nButtonLogin;
    private TextView nTextViewRegister;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        nTextUsername = (EditText) findViewById(R.id.login_username);
        nTextPassword = (EditText) findViewById(R.id.login_password);
        nButtonLogin = (Button) findViewById(R.id.login_button);
        nTextViewRegister = (TextView) findViewById(R.id.login_register);

        nTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registrationIntent);

            }
        });

        nButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = nTextUsername.getText().toString().trim();
                String password = nTextPassword.getText().toString().trim();
                boolean result = dataBaseHelper.checkUser(username,password);

                if (result == true) {
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(home);
                }
                else {
                    Toast.makeText(MainActivity.this, "Login Unsuccessful!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
