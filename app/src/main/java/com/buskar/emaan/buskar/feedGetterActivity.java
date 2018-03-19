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

import java.util.ArrayList;
import java.util.List;

public class feedGetterActivity extends AppCompatActivity {
    private ListView postsListView;  private ListAdapter postsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_getter);
showList();
    }


    public List<String> populateList() {

        List<String> postList = new ArrayList<String>();
        DatabaseHelperF openHelperClass = new DatabaseHelperF(this);

        // Then we need to get a readable database
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();

        // We need a a guy to read the database query. Cursor interface will do it for us
        //(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor cursor = sqliteDatabase.query(DatabaseHelperF.TABLE_NAME_POSTS, null, null, null, null, null, null);
        // Above given query, read all the columns and fields of the table

        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            String post1Username = cursor.getString(cursor.getColumnIndex(DatabaseHelperF.COLUMN_NAME_USERNAME));
            String post1body = cursor.getString(cursor.getColumnIndex(DatabaseHelperF.COLUMN_NAME_BODY));
            String post1Loc = cursor.getString(cursor.getColumnIndex(DatabaseHelperF.COLUMN_NAME_HASHTAGLOC));
            String post1Prob = cursor.getString(cursor.getColumnIndex(DatabaseHelperF.COLUMN_NAME_HASHTAGPROB));
            String post1score = cursor.getString(cursor.getColumnIndex(DatabaseHelperF.COLUMN_NAME_BUMPSCORE));

            postList.add("***************************************");
           postList.add(post1Username);
            postList.add(post1body);
            postList.add(post1Loc);
            postList.add(post1Prob);
            postList.add(post1score);
            postList.add("***************************************");
        }
        // If you don't close the database, you will get an error
        sqliteDatabase.close();

        return postList;
    }

    private void showList(){

        postsListView = (ListView) findViewById(R.id.feedList);
        postsListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, populateList());

        postsListView.setAdapter(postsListAdapter);
    }
    }
