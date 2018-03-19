package com.buskar.emaan.buskar;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class addVehicleActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vNameEditText;
    private EditText vTypeEditText;
    private EditText vRouteEditText;
    private Button cancelButton;
    private Button saveButton;

    private ArrayList<VehicleDetails> vehicleDetailsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        vNameEditText = (EditText)findViewById(R.id.reg_name);
        vTypeEditText = (EditText) findViewById(R.id.reg_type);
        vRouteEditText = (EditText) findViewById(R.id.reg_route);

        //cancelButton = (Button) findViewById(R.id.insertNewUgraduate_cancel_button);
        //cancelButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.btnAddVehicle);
        saveButton.setOnClickListener(this);

        vehicleDetailsArrayList = new ArrayList<VehicleDetails>();

    }
    @Override
    public void onClick(View v) {
        //if(v.getId() == R.id.insertNewUgraduate_cancel_button){
        //  finish();
        //}
        if(v.getId() == R.id.btnAddVehicle){
            // Get the values provided by the user via the UI
            String providedvName = vNameEditText .getText().toString();
            String providedvType = vTypeEditText.getText().toString();
            String providedvRoute = vRouteEditText.getText().toString();

            // Pass above values to the setter methods in POJO class
            VehicleDetails vObj = new VehicleDetails();
            vObj.setVName(providedvName);
            vObj.setVType(providedvType);
            vObj.setVRoute(providedvRoute);

            // Add an undergraduate with his all details to a ArrayList
            vehicleDetailsArrayList.add(vObj);

            // Inserting undergraduate details to the database is doing in a separate method
            insertVehicle(vObj);

            // Release from the existing UI and go back to the previous UI
            finish();
        }
    }

    public void insertVehicle(VehicleDetails Obj){

        // First we have to open our DbHelper class by creating a new object of that
        DatabaseHelperV DbHelperObj = new DatabaseHelperV(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = DbHelperObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(DatabaseHelperV.COLUMN_NAME_BNAME, Obj.getVName());
        contentValues.put(DatabaseHelperV.COLUMN_NAME_TYPE, Obj.getVType());
        contentValues.put(DatabaseHelperV.COLUMN_NAME_ROUTE, Obj.getVRoute());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(DatabaseHelperV.TABLE_NAME_VEHICLES, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
         sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }

}
