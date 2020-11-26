package com.sample.apps.is4447.gobusker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class buskerRegister extends AppCompatActivity {
    private FirebaseAuth mAuth;

    Button register;
    EditText email, password, firstname, secondname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busker_register);
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.etbuskerEmailRegister);
        firstname = (EditText) findViewById(R.id.etBuskerFirtsName);
        secondname = (EditText) findViewById(R.id.etbuskerSecondName);
        password = (EditText) findViewById(R.id.etbuskerPasswordRegister);

        register = (Button) findViewById(R.id.btnbuskerRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailreg = email.getText().toString();
                String firstnamereg = firstname.getText().toString();
                String secondnamereg = secondname.getText().toString();
                String passwordreg = password.getText().toString();

                if (firstnamereg.isEmpty()) {
                    firstname.setError("Please enter first name");
                    firstname.requestFocus();
                }
                if (secondnamereg.isEmpty()) {
                    secondname.setError("Please enter second name");
                    secondname.requestFocus();
                }
                if (emailreg.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailreg).matches()) {
                    email.setError("Please enter valid email");
                    email.requestFocus();
                }
                if (passwordreg.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                if (passwordreg.length() < 6) {
                    password.setError("Password must be longer than 6 characters");
                    password.requestFocus();
                }

                mAuth.createUserWithEmailAndPassword(emailreg, passwordreg)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Busker busker = new Busker(firstnamereg, secondnamereg, emailreg);
                                    FirebaseDatabase.getInstance().getReference("Buskers")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(busker).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(buskerRegister.this, "Busker has been registered successfully", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(buskerRegister.this, "Registration has failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                        });


            }

        });


    }
}