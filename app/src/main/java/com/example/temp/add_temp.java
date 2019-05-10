package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class add_temp extends AppCompatActivity {

    Button back,add_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_temp);

        back = findViewById(R.id.back);
        add_temp = findViewById(R.id.add_temp);

        add_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad_tp = new Intent(add_temp.this,setting.class);
                startActivity(ad_tp);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(add_temp.this,setting.class);
                startActivity(back);
            }
        });
    }
}
