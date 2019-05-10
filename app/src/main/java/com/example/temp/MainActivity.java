package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button bt_camera,bt_temp,bt_setting,bt_out,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_camera = findViewById(R.id.bt_camera);
        bt_temp = findViewById(R.id.bt_temp);
        bt_setting = findViewById(R.id.bt_setting);
        bt_out = findViewById(R.id.bt_out);
        back = findViewById(R.id.back);

        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_ca = new Intent(MainActivity.this,camera.class);
                startActivity(bt_ca);
            }
        });

             bt_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_te = new Intent(MainActivity.this,temp.class);
                startActivity(bt_te);
            }
        });

        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_st = new Intent(MainActivity.this,setting.class);
                startActivity(bt_st);
            }
        });


        bt_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bt_out = new Intent(MainActivity.this,login.class);
                startActivity(bt_out);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MainActivity.this,login.class);
                startActivity(back);
            }
        });
    }
}
