package com.buskar.emaan.buskar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class searchByLocationActivity extends AppCompatActivity {
    EditText start;
    EditText end;
    String startLoc;
    String endLoc;
    private ListView VehiclesNamesListView;  private ListAdapter vehicleListAdapter;
Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_location);

        start = (EditText) findViewById(R.id.startLoc);
        end = (EditText) findViewById(R.id.endLoc);

       search= (Button) findViewById(R.id.buttonSearchLoc);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList(view);
            }
        });
    }

    public List<String> populateList() {

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
            if (vRoute.contains(startLoc)&& vRoute.contains(endLoc)) {
                vehiclesNamesList.add(vName);
            }





        } sqliteDatabase.close();
        return vehiclesNamesList;
    }
    private void showList(View view){
        startLoc = start.getText().toString();
        endLoc = end.getText().toString();
        VehiclesNamesListView = (ListView) findViewById(R.id.VehiclesList);
        vehicleListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());

        VehiclesNamesListView.setAdapter(vehicleListAdapter);
    }
}