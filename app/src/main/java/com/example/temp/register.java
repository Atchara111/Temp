package com.example.temp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class register extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputID,InputPassword,InputPhoneNumber,InputName,Inputaddress;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = findViewById(R.id.CreateAccountButton);
        InputID = findViewById(R.id.InputID);
        InputPassword = findViewById(R.id.InputPassword);
        InputPhoneNumber = findViewById(R.id.InputPhoneNumber);
        InputName = findViewById(R.id.InputName);
        Inputaddress = findViewById(R.id.InputAddress);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount()
    {
        String id = InputID.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
        String name = InputName.getText().toString();
        String address = Inputaddress.getText().toString();

        if (TextUtils.isEmpty(id))
    {
        Toast.makeText(this,"โปรดกรอกชื่อผู้ใช้ของคุณ",Toast.LENGTH_LONG).show();
    }
       else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"โปรดกรอกเบอร์โทรศัพท์ของคุณ",Toast.LENGTH_LONG).show();
        }
       else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"โปรดกรอกรหัสผ่านของคุณ",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"โปรดกรอกชื่อ",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this,"โปรดกรอกที่อยู่",Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingBar.setTitle("สร้างบัญชี");
            loadingBar.setMessage("โปรดรอสักครู่ขณะที่เรากำลังตรวจสอบข้อมูลประจำตัว");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumeber(id,phone,password,name,address);
        }

    }

    private void ValidatephoneNumeber(final String id, final String address, final String name, final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String, Object> userdataMap = new HashMap<>();

                    userdataMap.put("phone",phone);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);
                    userdataMap.put("id",id);
                    userdataMap.put("address",address);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(register.this,"ยินดีด้วยคุณสมัครสมาชิกสำเร็จ",Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(register.this,login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(register.this,"มีบางอย่างผิดพลาด โปรดลองอีกครั้ง",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else
                    {
                        Toast.makeText(register.this,"this" + phone + "already exists",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                        Toast.makeText(register.this,"โปรดลองอีกครั้งโดยใช้หมายเลขโทรศัพท์อื่น",Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(register.this,login.class);
                        startActivity(intent);

                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

