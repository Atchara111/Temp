package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class edit_about extends AppCompatActivity {

    Button back,rc_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_about);

        back = findViewById(R.id.back);
        rc_about = findViewById(R.id.rc_about);

        rc_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rcab = new Intent(edit_about.this,about.class);
                startActivity(rcab);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(edit_about.this,about.class);
                startActivity(back);
            }
        });
    }
}
