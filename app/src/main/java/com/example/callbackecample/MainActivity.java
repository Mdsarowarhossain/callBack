package com.example.callbackecample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiCall apicall = new ApiCall(new Handler(Looper.getMainLooper()));
        OnApiresposne onApiresposne = new OnApiresposne() {
            @Override
            public void onSuccessResponse(String result, int statuscode) {
                Toast.makeText(MainActivity.this, "" + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(String errorMessage) {

            }
        };


        apicall.getApiRequest("https://www.google.com", onApiresposne);

    }
}