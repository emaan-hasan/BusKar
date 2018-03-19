package com.buskar.emaan.buskar;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class deleteUserActivity extends AppCompatActivity implements View.OnClickListener{
EditText userToDelete;
    Button delete;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        userToDelete=(EditText) findViewById(R.id.editTextUserDel);
        delete=(Button) findViewById(R.id.buttonDelete);
        delete.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        userEmail=userToDelete.getText().toString();
        DatabaseHelper openHelperClass = new DatabaseHelper(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
// Define 'where' part of query.
        String whereClause = DatabaseHelper.COLUMN_NAME_email + " = ?";
// Specify arguments in placeholder order.
        String[] whereArgs = { userEmail };
// Issue SQL statement.
        sqliteDatabase.delete(DatabaseHelper.TABLE_NAME_USERS, whereClause, whereArgs);
    }
}
