package com.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.anim.test.Items;


import java.util.ArrayList;

/**
 * Created by android on 1/15/2018.
 */

public class DatabaseManager {

    public SQLiteDatabase sqLiteDatabase;
    public DatabaseHelper databaseHelper;
    public Items wallpaperItem;


    public DatabaseManager(Context context){
        databaseHelper = new DatabaseHelper(context);
        wallpaperItem = new Items();
        sqLiteDatabase = databaseHelper.getReadableDatabase();


    }


    public void open() throws SQLiteException{
        sqLiteDatabase = databaseHelper.getWritableDatabase();

    }

    public void close() throws SQLiteException{
        sqLiteDatabase.close();
    }

    public Items createWallpaperList (final Items wallpaperItem){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("link" , wallpaperItem.getLink());
        contentValues.put("wallpaper_id" , wallpaperItem.getId());
        sqLiteDatabase.insert(DatabaseHelper.TABLE_FAVOURITE_WALLPAPER , null, contentValues);
        return wallpaperItem;
    }


    public void addIntoDatabase(String link, int id ){

        wallpaperItem.setLink(link);
        wallpaperItem.setId(id);
        createWallpaperList(wallpaperItem);
    }

    public ArrayList<Items> getAllFavouriteWallpaperData(){
        open();
        final ArrayList<Items> wallpaperItems = new ArrayList<>();
        final Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLE_FAVOURITE_WALLPAPER, null);

        cursor.moveToLast();
        while (!cursor.isBeforeFirst()){
            final Items wallpaperItem = new Items();
            wallpaperItem.setId(cursor.getInt(0));
            wallpaperItem.setLink(cursor.getString(1));
            wallpaperItems.add(wallpaperItem);
            cursor.moveToPrevious();
        }

        return wallpaperItems;
    }


    public void deleteWallpaper(final Items wallpaperItem){
        sqLiteDatabase.delete(DatabaseHelper.TABLE_FAVOURITE_WALLPAPER , "link = '" + wallpaperItem.getLink() + "'", null);
    }

}
