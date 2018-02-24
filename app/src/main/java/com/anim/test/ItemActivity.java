package com.anim.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.lolipop.testwallpaper.R;
import com.kogitune.activitytransition.ActivityTransitionLauncher;
import com.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity{

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    public ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ItemAdapter(getApplicationContext() , getSortItems());
        int position = getSortItems().size();
        Log.d("Message" ,""+position );
        recyclerView.setAdapter(adapter);
    }


    public static ArrayList<Items> getSortItems() {
        ArrayList<Items> items = new ArrayList<>();
        items.add(new Items(2, "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
        items.add(new Items(3 , "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
        items.add(new Items(4 , "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
        return items;
    }

}
