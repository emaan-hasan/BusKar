package com.buskar.emaan.buskar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.buskar.emaan.buskar.R.id.buttonSearchBus;
import static com.buskar.emaan.buskar.R.id.buttonSearchLoc;

/**
 * Created by Emaan on 11/28/2017.
 */

public class tab1_fragment extends Fragment {
    private static final String tag= "tab1_frag";
    Button searchByBus; Button searchByLoc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab1_frag, container, false);
        searchByBus = (Button) view.findViewById(buttonSearchBus);
        searchByBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), searchByBusActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        searchByLoc = (Button) view.findViewById(buttonSearchLoc);
        searchByLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(getActivity(), searchByLocationActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });
        return view;

    }


}



