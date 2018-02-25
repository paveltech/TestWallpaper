package com.anim.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
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
import com.danimahardhika.android.helpers.animation.AnimationHelper;
import com.danimahardhika.android.helpers.core.DrawableHelper;
import com.databse.DatabaseManager;
import com.example.lolipop.testwallpaper.R;


import com.example.lolipop.testwallpaper.WallpaperDownloader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.squareup.picasso.Picasso;
import com.util.ImageConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lolipop on 2/25/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder>{

    public Context context;
    public ArrayList<Items> itemsArrayList;
    private final DisplayImageOptions.Builder mOptions;
    public CallBack callBack;

    public DatabaseManager databaseManager;
    public ArrayList<Items> wallpaperFavouriteList;



    public ItemAdapter(Context context , ArrayList<Items> itemsArrayList , CallBack callBack ){
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        mOptions = ImageConfig.getRawDefaultImageOptions();
        mOptions.resetViewBeforeLoading(true);
        this.callBack = callBack;
        mOptions.cacheInMemory(true);
        mOptions.cacheOnDisk(true);
        mOptions.displayer(new FadeInBitmapDisplayer(700));
        databaseManager = new DatabaseManager(context);
        wallpaperFavouriteList = new ArrayList<>();

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



        if (isFavourite(items.getLink()) == true){
            setFavorite(holder.love, Color.WHITE, position, true , true);
        }else {
            setFavorite(holder.love, Color.WHITE, position, true , false);
        }

        ViewCompat.setTransitionName(holder.imageView, "pavel");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.show(holder.getAdapterPosition() , items , holder.imageView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        ImageView love;
        CardView cardView;
        View view;
        @BindView(R.id.download)
        ImageView download;



        public CustomViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this , itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.card);
            love = (ImageView) itemView.findViewById(R.id.favorite);
            love.setOnClickListener(this);
            download.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int id = v.getId();
            Items items = itemsArrayList.get(position);

            if (id == R.id.favorite){

                if (isFavourite(items.getLink())==true){
                    databaseManager.deleteWallpaper(items);
                    setFavorite(love, Color.WHITE, position, true , false);

                }else if (isFavourite(items.getLink())==false){
                    setFavorite(love, Color.WHITE, position, true, true);
                    databaseManager.addIntoDatabase(items.getLink() , items.getId());
                }

            } else if (id == R.id.download){

            }

        }
    }

    public interface CallBack{
        void show(int position , Items items , ImageView imageView);
    }

    private void setFavorite(@NonNull ImageView imageView, @ColorInt int color, int position, boolean animate , boolean isFavo) {
        if (position < 0 || position > itemsArrayList.size()) return;

        //boolean isFavo = true;
        if (animate) {
            AnimationHelper.show(imageView)
                    .interpolator(new LinearOutSlowInInterpolator())
                    .callback(new AnimationHelper.Callback() {
                        @Override
                        public void onAnimationStart() {
                            imageView.setImageDrawable(DrawableHelper.getTintedDrawable(context,
                                    isFavo ? R.drawable.ic_toolbar_love : R.drawable.ic_toolbar_unlove, color));
                        }

                        @Override
                        public void onAnimationEnd() {

                        }
                    })
                    .start();
            return;
        }

        imageView.setImageDrawable(DrawableHelper.getTintedDrawable(context,
                isFavo ? R.drawable.ic_toolbar_love : R.drawable.ic_toolbar_unlove, color));
    }


    public boolean isFavourite(String songId) {
        wallpaperFavouriteList = databaseManager.getAllFavouriteWallpaperData();
        if (wallpaperFavouriteList.contains(new Items(songId))) {
            return true;
        }
        return false;
    }
}

