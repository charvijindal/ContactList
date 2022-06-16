package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList = new ArrayList<ModelClass>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

//        initData();
        checkPermission();




    }

    private void checkPermission() {

        if(ContextCompat.checkSelfPermission(MainActivity.this
        , Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }

        else{
            getContactList();
        }
    }

    private void getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor cursor = getContentResolver().query(uri, null, null, null, sort);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";

                Cursor phoneCursor = getContentResolver().query(uriPhone, null, selection,
                        new String[]{id}, null);

                if(phoneCursor.moveToNext()){
                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    ModelClass model = new ModelClass();
                    model.setContact1(name);
                    model.setContact2(number);

                    Uri uriEmail = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                    String selection1 = ContactsContract.CommonDataKinds.Email.CONTACT_ID+" =?";

                    Cursor emailCursor = getContentResolver().query(uriEmail, null, selection1,
                            new String[]{id}, null);
                    if (emailCursor.moveToNext()) {
                        @SuppressLint("Range") String email = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));


                        model.setEmail(email);

                        emailCursor.close();
                    }

                    phoneCursor.close();
                    userList.add(model);

                }

            }
            cursor.close();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, userList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getContactList();
        }
        else{
            Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            checkPermission();
        }
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        intent.putExtra("name", userList.get(position).getContact1());
        intent.putExtra("number", userList.get(position).getContact2());
        intent.putExtra("email", userList.get(position).getEmail());

        startActivity(intent);
    }
    //    private void initData() {
//        userList = new ArrayList<>();
//
//        userList.add(new ModelClass("WAJ","9090876756"));
//        userList.add(new ModelClass("BSQAJ","9090876756"));
//        userList.add(new ModelClass("ASHIJ","9090876756"));
//        userList.add(new ModelClass("LAJZKNA","9090876756"));
//        userList.add(new ModelClass("PABZUGAJ","9090876756"));
//        userList.add(new ModelClass("SHIHWAJ","9090876756"));
//        userList.add(new ModelClass("SSWAJ","9090876756"));
//        userList.add(new ModelClass("BSQSQAJ","9090876756"));
//        userList.add(new ModelClass("AQQSHIJ","9090876756"));
//        userList.add(new ModelClass("LQAJZKNA","9090876756"));
//        userList.add(new ModelClass("QPABZUGAJ","9090876756"));
//        userList.add(new ModelClass("QSHIHWAJ","9090876756"));
//
//    }


}