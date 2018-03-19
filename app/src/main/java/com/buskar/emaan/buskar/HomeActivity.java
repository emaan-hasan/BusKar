package com.buskar.emaan.buskar;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
private static final String TAG="HomeActivity";
   private SectionsPageAdpater mSectionsPageAdapter;
    private ViewPager mViewPager;
    Button searchByBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
Log.d(TAG, "onCreate, Starting.");
        mSectionsPageAdapter= new SectionsPageAdpater(getSupportFragmentManager());
        mViewPager= (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager, bundle);
TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    private void setupViewPager(ViewPager mViewPager, Bundle bundle) {
    SectionsPageAdpater adapter=new SectionsPageAdpater(getSupportFragmentManager());
        tab3_fragment obj=new tab3_fragment();
        adapter.addFragment(new tab1_fragment(), "TAB1");
        adapter.addFragment(new tab2_fragment(), "TAB2");
        obj.setArguments(bundle);
        adapter.addFragment(obj, "TAB3");
        mViewPager.setAdapter(adapter);
    }


}
