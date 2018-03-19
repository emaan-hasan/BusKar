package com.buskar.emaan.buskar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class accessUsersActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView usersNamesListView;


    // We need some kind of Adapter to made the connection between ListView UI component and SQLite data set.
    private ListAdapter userListAdapter;

    // We need this while we read the query using Cursor and pass data
    private ArrayList<UserDetails> usersArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_users);
        // Initialize UI components
        usersNamesListView = (ListView) findViewById(R.id.AccessUsers);
        usersNamesListView.setOnItemClickListener(this);

        usersArrayList = new ArrayList<UserDetails>();

        // For the third argument, we need a List that contains Strings.
        //We decided to display undergraduates names on the ListView.
        //Therefore we need to create List that contains undergraduates names
        userListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());

        usersNamesListView.setAdapter(userListAdapter);

    }
    @Override
    public void onClick(View v) {
        // Intent addNewVehicleIntent = new Intent(this, searchByBusActivity.class);
        //startActivity(addNewVehicleIntent);
    }

    public List<String> populateList(){

        // We have to return a List which contains only String values. Lets create a List first
        List<String> userNamesList = new ArrayList<String>();

        // First we need to make contact with the database we have created using the DbHelper class
        DatabaseHelper openHelperClass = new DatabaseHelper(this);

        // Then we need to get a readable database
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

        // We need a a guy to read the database query. Cursor interface will do it for us
        //(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor cursor = sqliteDatabase.query(DatabaseHelper.TABLE_NAME_USERS, null, null, null, null, null, null);
        // Above given query, read all the columns and fields of the table

        startManagingCursor(cursor);

        // Cursor object read all the fields. So we make sure to check it will not miss any by looping through a while loop
        while (cursor.moveToNext()) {
            // In one loop, cursor read one undergraduate all details
            // Assume, we also need to see all the details of each and every undergraduate
            // What we have to do is in each loop, read all the values, pass them to the POJO class
            //and create a ArrayList of undergraduates

            String vName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_NAME));
            String vEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_email));
            String vPwd = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_Password));

            // Finish reading one raw, now we have to pass them to the POJO
            UserDetails vClass = new UserDetails();
            vClass.setUserName(vName);
            vClass.setUserEmail(vEmail);
            vClass.setUserPassword(vPwd);

            // Lets pass that POJO to our ArrayList which contains undergraduates as type
            usersArrayList.add(vClass);

            // But we need a List of String to display in the ListView also.
            //That is why we create "uGraduateNamesList"
            userNamesList.add(vName);
        }

        // If you don't close the database, you will get an error
        sqliteDatabase.close();

        return userNamesList;
    }
    @Override
    protected void onResume() {
        super.onResume();
        usersArrayList = new ArrayList<UserDetails>();
        userListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        usersNamesListView.setAdapter(userListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        usersArrayList = new ArrayList<UserDetails>();
        userListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());
        usersNamesListView.setAdapter(userListAdapter);
    }
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        Toast.makeText(getApplicationContext(), "Clicked on :" + arg2, Toast.LENGTH_SHORT).show();

        // We want to redirect to another Activity when the user click an item on the ListView
        Intent usersIntent = new Intent(this, userDisplayActivity.class);

        // We have to identify what object, does the user clicked, because we are going to pass only clicked object details to the next activity
        // What we are going to do is, get the ID of the clicked item and get the values from the ArrayList which has
        //same array id.
        UserDetails clickedObject =  usersArrayList.get(arg2);

        // We have to bundle the data, which we want to pass to the other activity from this activity
        Bundle dataBundle = new Bundle();
        dataBundle.putString("clickedVehicleName", clickedObject.getUserName());
        dataBundle.putString("clickedVehicleType", clickedObject.getUserEmail());
        dataBundle.putString("clickedVehicleRoute", clickedObject.getUserPassword());

        // Attach the bundled data to the intent
        usersIntent.putExtras(dataBundle);

        // Start the Activity
        startActivity(usersIntent);

    }
}
