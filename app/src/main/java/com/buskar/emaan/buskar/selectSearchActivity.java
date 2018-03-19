package com.buskar.emaan.buskar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_search);

        Button searchBusButton = (Button) findViewById(R.id.buttonSearchBus);
        searchBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayBusSearch(view);
            }
        });

        Button searchlocationButton = (Button) findViewById(R.id.buttonSearchLoc);
        searchlocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLocSearch(view);
            }
        });
    }
    public void displayBusSearch(View view) {
        // Do something in response to button
        startActivity(new Intent(selectSearchActivity.this, searchByBusActivity.class));

    }
    public void displayLocSearch(View view) {
        // Do something in response to button
        startActivity(new Intent(selectSearchActivity.this, searchByLocationActivity.class));

    }
}
