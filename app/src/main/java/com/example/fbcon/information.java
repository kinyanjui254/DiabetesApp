package com.example.fbcon;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class information extends AppCompatActivity {

    private EditText first_name, last_name, Age, weight, address;
    private Button submit_information;
    Info info;
    FirebaseDatabase database;

    DatabaseReference databaseInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        Age = findViewById(R.id.Age);
        weight = findViewById(R.id.weight);
        address = findViewById(R.id.address);
        submit_information = findViewById(R.id.submit_information);

        database = FirebaseDatabase.getInstance();
        databaseInfo = database.getReference("Info");
        info = new Info();
    }

        private  void getValues(){
           //Info.setfname(first_name.getText().toString());
                info.setFname(first_name.getText().toString());
                info.setLname(last_name.getText().toString());
            info.setAge(Age.getText().toString());
            info.setWeit(weight.getText().toString());
            info.setAddres(address.getText().toString());
        }


        public void submit_information(View view){
            databaseInfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    getValues();
                    databaseInfo.child("user01").setValue(info);
                    Toast.makeText(information.this,"Info submitted", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
