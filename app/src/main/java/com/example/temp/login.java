package com.example.temp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.temp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private Button LoginButton,btnR;
    private EditText InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnR = (Button)findViewById(R.id.btnR);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(login.this,register.class);
                startActivity(reg);
            }
        });


        LoginButton = findViewById(R.id.login_btn);
        InputPassword = findViewById(R.id.login_password_input);
        InputPhoneNumber = findViewById(R.id.login_phone_number_input);
        loadingBar = new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              LoginUser();
            }
        });
    }
     private void LoginUser()
     {
           String phone = InputPhoneNumber.getText().toString();
           String password = InputPassword.getText().toString();

           if (TextUtils.isEmpty(phone))
     {
         Toast.makeText(this,"โปรดกรอกเบอร์โทรศัพท์ของคุณ",Toast.LENGTH_LONG).show();
     }
          else if (TextUtils.isEmpty(password))
     {
         Toast.makeText(this,"โปรดกรอกรหัสผ่านของคุณ",Toast.LENGTH_LONG).show();
     }
          else
           {
               loadingBar.setTitle("ชื่อเข้าใช้");
               loadingBar.setMessage("โปรดรอสักครู่ขณะที่เรากำลังตรวจสอบข้อมูล");
               loadingBar.setCanceledOnTouchOutside(false);
               loadingBar.show();


               AllowAccessToAccount(phone,password);

           }
     }

    private void AllowAccessToAccount(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(login.this,"เข้าสู่ระบบสำเร็จ",Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(login.this,MainActivity.class);
                            startActivity(intent);

                        }

                    }
                }
                else
                {
                    Toast.makeText(login.this,"Account with this" + phone + "number do not exists",Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
