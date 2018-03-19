package com.buskar.emaan.buskar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Emaan on 11/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    // Database attributes
    public static final String DB_NAME = "BusKar_db";
    public static final int DB_VERSION = 1;

    // Table attributes
    public static final String TABLE_NAME_USERS = "undergraduate_Users_table";
    public static final String COLUMN_NAME_NAME = "undergraduate_name_column";
    public static final String COLUMN_NAME_Password = "undergraduate_password_column";
    public static final String COLUMN_NAME_email = "undergraduate_email_column";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Called when the database is created for the first time.
    //This is where the creation of tables and the initial population of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // We need to check whether table that we are going to create is already exists.
        //Because this method get executed every time we created an object of this class.
        //"create table if not exists TABLE_NAME ( BaseColumns._ID integer primary key autoincrement, FIRST_COLUMN_NAME text not null, SECOND_COLUMN_NAME integer not null);"

        String sqlQueryToCreateUsersTable = "create table if not exists " + TABLE_NAME_USERS + " ( " + BaseColumns._ID + " integer primary key autoincrement, "
                + COLUMN_NAME_NAME + " text not null, "
                + COLUMN_NAME_Password + " text not null, "
                + COLUMN_NAME_email + " real not null);";
        // Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
        db.execSQL(sqlQueryToCreateUsersTable);
    }

    // onUpgrade method is use when we need to upgrade the database in to a new version
    //As an example, the first release of the app contains DB_VERSION = 1
    //Then with the second release of the same app contains DB_VERSION = 2
    //where you may have add some new tables or alter the existing ones
    //Then we need check and do relevant action to keep our pass data and move with the next structure
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2){
            // Upgrade the database
        }
    }
}
