package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String name = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");
        String email = getIntent().getStringExtra("email");

        TextView textView1 = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.number);
        TextView textView3 = findViewById(R.id.email);
//        ImageView imageView = findViewById(R.id.contactImage1);
        textView1.setText(name);
        textView2.setText(number);
        textView3.setText(email);





    }


}