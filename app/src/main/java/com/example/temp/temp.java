package com.example.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class temp extends AppCompatActivity {
    public DatabaseReference tessapp;
    private TextView show_temp;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        show_temp =(TextView) findViewById(R.id.show_temp);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tessapp = database.getReference();
        tessapp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                Map map1 = (Map) dataSnapshot.child("logDHT").getValue();
                String temperature = String.valueOf(map1.get("temperature"));
                show_temp.setText(temperature);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       back = findViewById(R.id.back);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent back = new Intent(temp.this,MainActivity.class);
               startActivity(back);
           }
       });


    }
}
