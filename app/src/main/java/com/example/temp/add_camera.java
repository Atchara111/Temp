package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class add_camera extends AppCompatActivity {

    Button back,add_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_camera);

        back = findViewById(R.id.back);
        add_camera = findViewById(R.id.add_camera);

        add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad_ca = new Intent(add_camera.this,setting.class);
                startActivity(ad_ca);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(add_camera.this,setting.class);
                startActivity(back);
            }
        });
    }
}

