package com.buskar.emaan.buskar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class VehiclesActivity extends AppCompatActivity {
    private String bundledVehicleName;
    private String bundledVehicleType;
    private String bundledVehicleRoute;
    private TextView vNameEditText;
    private TextView vTypeEditText;
    private TextView vRouteEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);

        vNameEditText = (TextView) findViewById(R.id.textViewName);
        vTypeEditText = (TextView) findViewById(R.id.textViewType);
        vRouteEditText = (TextView) findViewById(R.id.textViewRoute);

        Bundle takeBundledData = getIntent().getExtras();

        // First we need to get the bundle data that pass from the UndergraduateListActivity
        bundledVehicleName = takeBundledData.getString("clickedVehicleName");
        bundledVehicleType = takeBundledData.getString("clickedVehicleType");
        bundledVehicleRoute = takeBundledData.getString("clickedVehicleRoute");
        // Set the values that we extracted from the Bundle in the EditText fields
        vNameEditText.setText(bundledVehicleName);
        vTypeEditText.setText(bundledVehicleType);
        vRouteEditText.setText(bundledVehicleRoute);
    }
}
