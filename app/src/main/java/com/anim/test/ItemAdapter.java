package com.anim.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lolipop.testwallpaper.R;
import com.example.lolipop.testwallpaper.WallpaperBoardApplication;
import com.kogitune.activitytransition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lolipop on 2/25/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder>{

    public Context context;
    public ArrayList<Items> itemsArrayList;
    public ItemAdapter(Context context , ArrayList<Items> itemsArrayList ){
        this.context = context;
        this.itemsArrayList = itemsArrayList;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_latest_item_grid , null);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Items items = itemsArrayList.get(position);
        Log.d("Message" , ""+items.getLink());
        Picasso.with(context).load(items.getLink()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        ImageView favourite;
        CardView cardView;
        View view;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this , itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.card);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int id = v.getId();
            if (id == R.id.image){


                Toast.makeText(context.getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                /*
                if (WallpaperBoardApplication.sIsClickable) {
                    WallpaperBoardApplication.sIsClickable = false;
                    try {
                        Bitmap bitmap = null;
                        if (imageView.getDrawable() != null) {
                            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        }

                        final Intent intent = new Intent(context.getApplicationContext(), ImageShowActivity.class);
                        intent.putExtra("url", itemsArrayList.get(position).getLink());
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        ActivityTransitionLauncher.with((AppCompatActivity) context.getApplicationContext())
                                .from(imageView, "img")
                                .image(bitmap)
                                .launch(intent);



                    } catch (Exception e) {
                        WallpaperBoardApplication.sIsClickable = true;
                    }


                }

                */
            }
        }
    }
}
