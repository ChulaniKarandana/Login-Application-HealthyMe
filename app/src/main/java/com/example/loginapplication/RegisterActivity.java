package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText nTextUsername;
    private EditText nTextPassword;
    private EditText nTextCnfPassword;
    private Button nButtonRegister;
    private TextView nTextViewLogin;
    DataBaseHelper dataBaseHelperRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nTextUsername = (EditText) findViewById(R.id.register_username);
        nTextPassword = (EditText) findViewById(R.id.register_password);
        nTextCnfPassword = (EditText) findViewById(R.id.register_cnfpassword);
        nButtonRegister = (Button) findViewById(R.id.register_button);
        nTextViewLogin = (TextView) findViewById(R.id.register_login);
        dataBaseHelperRegister = new DataBaseHelper(this);

        nTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(loginIntent);


            }
        });

        nButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = nTextUsername.getText().toString().trim();
                String password = nTextPassword.getText().toString().trim();
                String cnfpassword = nTextCnfPassword.getText().toString().trim();

                if (password.equals(cnfpassword)) {
                    Long result = dataBaseHelperRegister.registerUser(username,password);

                    if (result > 0) {
                        Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent moveto = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveto);
                    }
                    else {

                        Toast.makeText(RegisterActivity.this, "Registration Unsuccessful!!", Toast.LENGTH_SHORT).show();
                    }
                }

                else {
                    Toast.makeText(RegisterActivity.this, "Passwords are not matching!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
