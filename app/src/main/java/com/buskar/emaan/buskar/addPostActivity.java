package com.buskar.emaan.buskar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addPostActivity extends AppCompatActivity implements View.OnClickListener {
private EditText postBody;
    private EditText postLocHashtag;
    private EditText postProbHashtag;
    private EditText postUserName;
    private double bumpscore=0;
    private Button addPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        postBody=(EditText) findViewById(R.id.editTextPostBody);
        postLocHashtag=(EditText) findViewById(R.id.editTextLocationHashtag);
        postProbHashtag=(EditText) findViewById(R.id.editTextProblemHashtag);
        postUserName=(EditText) findViewById(R.id.editTextUserName);
    addPost=(Button) findViewById(R.id.buttonAddPost);
        addPost.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
            // Get the values provided by the user via the UI
            String providedpostBody = postBody .getText().toString();
            String providedpostLocHashtag = postLocHashtag.getText().toString();
            String providedpostProbHashtag = postProbHashtag.getText().toString();
        String providedpostUserName = postUserName.getText().toString();
        Double providedbumpscore=bumpscore;
            // Pass above values to the setter methods in POJO class
            UpdatesDetails vObj = new UpdatesDetails();
            vObj.setPostBody(providedpostBody);
            vObj.setPostHashtagLoc(providedpostLocHashtag);
            vObj.setPostHashtagProb(providedpostProbHashtag);
        vObj.setPostUsername(providedpostUserName);
        vObj.setPostScore(providedbumpscore);

        // Inserting undergraduate details to the database is doing in a separate method
            insertPost(vObj);

            // Release from the existing UI and go back to the previous UI
            finish();

    }
    public void insertPost(UpdatesDetails Obj){

        // First we have to open our DbHelper class by creating a new object of that
        DatabaseHelperF DbHelperObj = new DatabaseHelperF(this);

        // Then we need to get a writable SQLite database, because we are going to insert some values
        // SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.
        SQLiteDatabase sqliteDatabase = DbHelperObj.getWritableDatabase();

        // ContentValues class is used to store a set of values that the ContentResolver can process.
        ContentValues contentValues = new ContentValues();

        // Get values from the POJO class and passing them to the ContentValues class
        contentValues.put(DatabaseHelperF.COLUMN_NAME_BODY, Obj.getPostBody());
        contentValues.put(DatabaseHelperF.COLUMN_NAME_HASHTAGLOC, Obj.getPostHashtagLoc());
        contentValues.put(DatabaseHelperF.COLUMN_NAME_HASHTAGPROB, Obj.getPostHashtagProb());
        contentValues.put(DatabaseHelperF.COLUMN_NAME_USERNAME, Obj.getPostUsername());
        contentValues.put(DatabaseHelperF.COLUMN_NAME_BUMPSCORE, Obj.getPostScore());
        // Now we can insert the data in to relevant table
        // I am going pass the id value, which is going to change because of our insert method, to a long variable to show in Toast
        long affectedColumnId = sqliteDatabase.insert(DatabaseHelperF.TABLE_NAME_POSTS, null, contentValues);

        // It is a good practice to close the database connections after you have done with it
        sqliteDatabase.close();

        // I am not going to do the retrieve part in this post. So this is just a notification for satisfaction ;-)
        Toast.makeText(this, "Values inserted column ID is :" + affectedColumnId, Toast.LENGTH_SHORT).show();

    }


}
