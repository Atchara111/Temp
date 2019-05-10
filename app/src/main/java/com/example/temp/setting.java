package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class setting extends AppCompatActivity {

    Button back;
    TextView ed_about,add_temp,add_camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        back = findViewById(R.id.back);
        ed_about = findViewById(R.id.ed_about);
        add_temp = findViewById(R.id.add_temp);
        add_camera = findViewById(R.id.add_camera);

        ed_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ed_ab = new Intent(setting.this,about.class);
                startActivity(ed_ab);
            }
        });

        add_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_temp = new Intent(setting.this,add_temp.class);
                startActivity(add_temp);
            }
        });

        add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_camera = new Intent(setting.this,add_camera.class);
                startActivity(add_camera);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(setting.this,MainActivity.class);
                startActivity(back);
            }
        });
    }
}
