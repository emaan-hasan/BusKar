package com.buskar.emaan.buskar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button busButton = (Button) findViewById(R.id.buttonAddBus);
        busButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAddBus(view);
            }
        });



        Button usersButton = (Button) findViewById(R.id.buttonAccessUsers);
        usersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoAccessUsers(view);
            }
        });
    }
    public void gotoAddBus(View view) {
        // Do something in response to button
        startActivity(new Intent(adminActivity.this, addVehicleActivity.class));

    }
    public void gotoAccessBuses(View view) {
        // Do something in response to button
        startActivity(new Intent(adminActivity.this, userDisplayActivity.class));

    }
    public void gotoAccessUsers(View view) {
        // Do something in response to button
        startActivity(new Intent(adminActivity.this, accessUsersActivity.class));

    }
}
