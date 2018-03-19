package com.buskar.emaan.buskar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main_splash);
        // Start home activity
        startActivity(new Intent(MainActivity_splash.this, LoginActivity.class));

        // close splash activity
        finish();
    }



}
