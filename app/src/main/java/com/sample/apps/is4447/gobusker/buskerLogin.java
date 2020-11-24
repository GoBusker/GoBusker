package com.sample.apps.is4447.gobusker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class buskerLogin extends AppCompatActivity {
    EditText buskerLoginEmail, buskerLoginPassword;
    Button buskerLogin;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_login);
        db = new DataBaseHelper( this);
        buskerLoginEmail = (EditText) findViewById(R.id.etBuskerLoginEmail);
        buskerLoginPassword = (EditText) findViewById(R.id.etBuskerLoginPassword);
        buskerLogin = (Button) findViewById(R.id.btnBuskerLogin);

        buskerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = buskerLoginEmail.getText().toString();
                String password = buskerLoginPassword.getText().toString();
                Boolean checkEmailpass = db.emailpassword(email, password);
                if(checkEmailpass==true) {
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                }  else{
                        Toast.makeText(getApplicationContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}