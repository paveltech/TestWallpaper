package com.anim.test;


import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.widget.ImageView;

import com.example.lolipop.testwallpaper.R;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemActivity extends AppCompatActivity implements ItemAdapter.CallBack{

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
        adapter = new ItemAdapter(getApplicationContext() , getSortItems() , this);
        int position = getSortItems().size();
        Log.d("Message" ,""+position );
        recyclerView.setAdapter(adapter);
    }


    public static ArrayList<Items> getSortItems() {
        ArrayList<Items> items = new ArrayList<>();
        items.add(new Items(2, "https://static.pexels.com/photos/248797/pexels-photo-248797.jpeg"));
        items.add(new Items(3 , "https://upload.wikimedia.org/wikipedia/commons/9/95/Big_Pine_landscape.jpg"));
        items.add(new Items(4 , "https://upload.wikimedia.org/wikipedia/commons/9/95/Big_Pine_landscape.jpg"));
        return items;
    }

    @Override
    public void show(int position, Items items, ImageView imageView) {
        Intent intent = new Intent(this, ImageShowActivity.class);
        intent.putExtra("item", items);
        intent.putExtra("view", ViewCompat.getTransitionName(imageView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView));

        startActivity(intent, options.toBundle());
    }
}
