package com.buskar.emaan.buskar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Emaan on 11/28/2017.
 */
import java.util.ArrayList;
import java.util.List;

import static com.buskar.emaan.buskar.R.id.textViewEmail;
import static com.buskar.emaan.buskar.R.id.textViewName;
import static com.buskar.emaan.buskar.R.id.post;
public class tab3_fragment extends Fragment {
    private static final String tag= "tab3_frag";
String email; String userName; ArrayList<String> postList = new ArrayList<String>(); TextView textEmail; TextView textName  ;
    private ListView postsListView;  private ListAdapter postsListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab3_frag, container, false);

        Bundle bundle=getArguments();
        if(bundle!=null){

                email=bundle.getString("Login");
                userName=bundle.getString("name");
                postList=bundle.getStringArrayList("posts");

        }
        textEmail=(TextView) view.findViewById(textViewEmail);
        textEmail.setText(email);
        textName=(TextView) view.findViewById(textViewName);
        textName.setText(userName);
        postsListView = (ListView) view.findViewById(post);
        postsListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, postList);

        postsListView.setAdapter(postsListAdapter);

        return view;
    }

}
