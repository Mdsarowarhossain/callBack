package com.example.callbackecample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Adfendo adfendo = new Adfendo(this);
        adfendo.adViewBanner(this, new AdViewBanner(MainActivity.this), new OnClickItem() {

            @Override
            public void onclick() {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }
}