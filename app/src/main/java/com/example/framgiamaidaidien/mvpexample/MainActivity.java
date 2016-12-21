package com.example.framgiamaidaidien.mvpexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HOMEFRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        if (null == fm.findFragmentById(R.id.content_layout)) {
            HomeFragment homeFragment = new HomeFragment();
            fm.beginTransaction().add(R.id.content_layout, homeFragment, TAG).commit();
        }
    }
}
