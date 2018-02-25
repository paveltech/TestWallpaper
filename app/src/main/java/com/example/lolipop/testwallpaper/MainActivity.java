package com.example.lolipop.testwallpaper;

import android.content.Intent;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.anim.test.ItemActivity;
import com.example.lolipop.testwallpaper.com.pojo.Wallpaper;
import com.tasks.WallpaperApplyTask;

public class MainActivity extends AppCompatActivity {

    Wallpaper wallpaper = Wallpaper.Builder().build();
    RectF rectF = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                WallpaperApplyTask.prepare(MainActivity.this)
                        .wallpaper(wallpaper)
                        .to(WallpaperApplyTask.Apply.HOMESCREEN_LOCKSCREEN)
                        .start(AsyncTask.THREAD_POOL_EXECUTOR);
                        */

                //Intent intent = new Intent(MainActivity.this , ItemActivity.class);
                //startActivity(intent);

                WallpaperDownloader.prepare(getApplicationContext()).wallpaper(wallpaper).start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
