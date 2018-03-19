package com.buskar.emaan.buskar;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;


import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText userNameEditText;
    private EditText userPasswordEditText;
    private EditText userEmailEditText;
    private Button cancelButton;
    private Button saveButton;

    private ArrayList<UserDetails> userDetailsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userNameEditText = (EditText)findViewById(R.id.reg_fullname);
        userPasswordEditText = (EditText) findViewById(R.id.reg_password);
        userEmailEditText = (EditText) findViewById(R.id.reg_email);

        //cancelButton = (Button) findViewById(R.id.insertNewUgraduate_cancel_button);
        //cancelButton.setOnClickListener(this);
        saveButton = (Button) findViewById(R.id.btnRegister);
        saveButton.setOnClickListener(this);

        userDetailsArrayList = new ArrayList<UserDetails>();

    }
    @Override
    public void onClick(View v) {
        //if(v.getId() == R.id.insertNewUgraduate_cancel_button){
        //  finish();
        //}
        if (v.getId() == R.id.btnRegister) {
            // Get the values provided by the user via the UI
            String providedUserName = userNameEditText.getText().toString();
            String providedUserPassword = userPasswordEditText.getText().toString();
            String providedUserEmail = userEmailEditText.getText().toString();

            // Pass above values to the setter methods in POJO class if unique
            DatabaseHelper openHelperClass = new DatabaseHelper(this);
            SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
            Cursor cursor = sqliteDatabase.query(DatabaseHelper.TABLE_NAME_USERS, null, null, null, null, null, null);
            startManagingCursor(cursor);
            boolean toAdd = true;
            // Cursor object read all the fields. So we make sure to check it will not miss any by looping through a while loop
            while (cursor.moveToNext()) {
                String vEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME_email));
                if (providedUserEmail.equals(vEmail)) {
                    toAdd = false;

                }

            }

            sqliteDatabase.close();
            if (toAdd == true) {
                UserDetails userObj = new UserDetails();
                userObj.setUserName(providedUserName);
                userObj.setUserPassword(providedUserPassword);
                userObj.setUserEmail(providedUserEmail);

                // Add an undergraduate with his all details to a ArrayList
                userDetailsArrayList.add(userObj);

                // Inserting undergraduate details to the database is doing in a separate method
                insertUser(userObj);

                // Release from the existing UI and go back to the previous UI
                finish();
            }
            if (toAdd == false){Toast.makeText(this, "Email not unique", Toast.LENGTH_SHORT).show();}
        }
    }
    public void insertUser(UserDetails Obj){

        // First we have to open our DbHelper class by creating a new object of that
        DatabaseHelper DbHelperObj = new DatabaseHelper(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = DbHelperObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(DatabaseHelper.COLUMN_NAME_NAME, Obj.getUserName());
        contentValues.put(DatabaseHelper.COLUMN_NAME_Password, Obj.getUserPassword());
        contentValues.put(DatabaseHelper.COLUMN_NAME_email, Obj.getUserEmail());

        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(DatabaseHelper.TABLE_NAME_USERS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }




}
