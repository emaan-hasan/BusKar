package com.buskar.emaan.buskar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class searchByBusActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView VehiclesNamesListView;


    // We need some kind of Adapter to made the connection between ListView UI component and SQLite data set.
    private ListAdapter vehicleListAdapter;

    // We need this while we read the query using Cursor and pass data
    private ArrayList<VehicleDetails> vehicleArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_bus);
        // Initialize UI components
        VehiclesNamesListView = (ListView) findViewById(R.id.VehiclesList);
        VehiclesNamesListView.setOnItemClickListener(this);

        vehicleArrayList = new ArrayList<VehicleDetails>();

        // For the third argument, we need a List that contains Strings.
        //We decided to display undergraduates names on the ListView.
        //Therefore we need to create List that contains undergraduates names
        vehicleListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());

        VehiclesNamesListView.setAdapter(vehicleListAdapter);

    }
    @Override
    public void onClick(View v) {
       // Intent addNewVehicleIntent = new Intent(this, searchByBusActivity.class);
        //startActivity(addNewVehicleIntent);
    }

    public List<String> populateList(){

        // We have to return a List which contains only String values. Lets create a List first
        List<String> vehiclesNamesList = new ArrayList<String>();

        // First we need to make contact with the database we have created using the DbHelper class
        DatabaseHelperV openHelperClass = new DatabaseHelperV(this);

        // Then we need to get a readable database
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

        // We need a a guy to read the database query. Cursor interface will do it for us
        //(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor cursor = sqliteDatabase.query(DatabaseHelperV.TABLE_NAME_VEHICLES, null, null, null, null, null, null);
        // Above given query, read all the columns and fields of the table

        startManagingCursor(cursor);

        // Cursor object read all the fields. So we make sure to check it will not miss any by looping through a while loop
        while (cursor.moveToNext()) {
            // In one loop, cursor read one undergraduate all details
            // Assume, we also need to see all the details of each and every undergraduate
            // What we have to do is in each loop, read all the values, pass them to the POJO class
            //and create a ArrayList of undergraduates

            String vName = cursor.getString(cursor.getColumnIndex(DatabaseHelperV.COLUMN_NAME_BNAME));
            String vType = cursor.getString(cursor.getColumnIndex(DatabaseHelperV.COLUMN_NAME_TYPE));
            String vRoute = cursor.getString(cursor.getColumnIndex(DatabaseHelperV.COLUMN_NAME_ROUTE));

            // Finish reading one raw, now we have to pass them to the POJO
            VehicleDetails vClass = new VehicleDetails();
            vClass.setVName(vName);
            vClass.setVType(vType);
            vClass.setVRoute(vRoute);

            // Lets pass that POJO to our ArrayList which contains undergraduates as type
            vehicleArrayList.add(vClass);

            // But we need a List of String to display in the ListView also.
            //That is why we create "uGraduateNamesList"
            vehiclesNamesList.add(vName);
        }

        // If you don't close the database, you will get an error
        sqliteDatabase.close();

        return vehiclesNamesList;
    }
    @Override
    protected void onResume() {
        super.onResume();
        vehicleArrayList = new ArrayList<VehicleDetails>();
        vehicleListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        VehiclesNamesListView.setAdapter(vehicleListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vehicleArrayList = new ArrayList<VehicleDetails>();
        vehicleListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        VehiclesNamesListView.setAdapter(vehicleListAdapter);
    }
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        Toast.makeText(getApplicationContext(), "Clicked on :" + arg2, Toast.LENGTH_SHORT).show();

        // We want to redirect to another Activity when the user click an item on the ListView
        Intent vehiclesIntent = new Intent(this, VehiclesActivity.class);

        // We have to identify what object, does the user clicked, because we are going to pass only clicked object details to the next activity
        // What we are going to do is, get the ID of the clicked item and get the values from the ArrayList which has
        //same array id.
        VehicleDetails clickedObject =  vehicleArrayList.get(arg2);

        // We have to bundle the data, which we want to pass to the other activity from this activity
        Bundle dataBundle = new Bundle();
        dataBundle.putString("clickedVehicleName", clickedObject.getVName());
        dataBundle.putString("clickedVehicleType", clickedObject.getVType());
        dataBundle.putString("clickedVehicleRoute", clickedObject.getVRoute());

        // Attach the bundled data to the intent
        vehiclesIntent.putExtras(dataBundle);

        // Start the Activity
        startActivity(vehiclesIntent);

    }
}
