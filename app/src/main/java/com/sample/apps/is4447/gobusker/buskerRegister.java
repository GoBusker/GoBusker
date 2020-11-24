package com.sample.apps.is4447.gobusker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class buskerRegister extends AppCompatActivity {
    DataBaseHelper db;

    EditText email, password1, password2;
    Button register, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);

        email = (EditText) findViewById(R.id.etBuskerEmail);
        password1 = (EditText) findViewById(R.id.etPassword1);
        password2 = (EditText) findViewById(R.id.etPassword2);
        register = (Button) findViewById(R.id.btnRegister);
        login = (Button) findViewById(R.id.btnLoginBuskerReg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(buskerRegister.this,buskerLogin.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String password1String = password1.getText().toString();
                String password2String = password2.getText().toString();
                if(emailString.equals("")||password1String.equals("")||password2String.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(password1String.equals(password2String)){
                        Boolean checkEmail = db.checkEmail(emailString);
                        if(checkEmail==true){
                            Boolean insert = db.insert(emailString, password1String);
                            Log.d("test", insert.toString());
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    }
                }

        });
    }
}