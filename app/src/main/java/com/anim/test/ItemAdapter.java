package com.anim.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
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
    public ItemAdapter(Context context , ArrayList<Items> itemsArrayList , CallBack callBack ){
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        mOptions = ImageConfig.getRawDefaultImageOptions();
        mOptions.resetViewBeforeLoading(true);
        this.callBack = callBack;
        mOptions.cacheInMemory(true);
        mOptions.cacheOnDisk(true);
        mOptions.displayer(new FadeInBitmapDisplayer(700));

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

    public class CustomViewHolder extends RecyclerView.ViewHolder{

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

    }

    public interface CallBack{
        void show(int position , Items items , ImageView imageView);
    }
    }

