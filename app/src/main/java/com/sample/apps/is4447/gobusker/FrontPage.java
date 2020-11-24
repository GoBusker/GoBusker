package com.sample.apps.is4447.gobusker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity {
        Button busker, fan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        busker = (Button) findViewById(R.id.btnGoToBusker);
        fan = (Button) findViewById(R.id.btnGoToFan);

        busker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontPage.this,buskerRegister.class);
                startActivity(i);
            }
        });
    }
}