package com.buskar.emaan.buskar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;
import static com.buskar.emaan.buskar.R.id.buttonAddPost;
import static com.buskar.emaan.buskar.R.id.buttonFeed;
/**
 * Created by Emaan on 11/28/2017.
 */

public class tab2_fragment extends Fragment {
Button addPost;
    Button feed;

    private static final String tag= "tab2_frag";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab2_frag, container, false);

        addPost = (Button) view.findViewById(buttonAddPost);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), addPostActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        feed = (Button) view.findViewById(buttonFeed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity2 = new Intent(getActivity(), feedGetterActivity.class);
                startActivity(intentLoadNewActivity2);
            }
        });

    return view;}




}

