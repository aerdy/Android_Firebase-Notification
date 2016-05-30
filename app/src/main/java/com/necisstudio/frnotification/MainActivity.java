package com.necisstudio.frnotification;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("android");
        try {
            Intent intent = new Intent(getApplicationContext(),ServiceNotifidService.class);
            startActivity(intent);
        }catch (Exception e){
            FirebaseCrash.log("Activity created");
            FirebaseCrash.report(new Exception(e.getMessage()));
        }
    }
}
