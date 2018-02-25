package com.anim.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lolipop on 2/25/2018.
 */

public class Items implements Parcelable{

    public int id;
    public String link;

    public Items(){

    }


    protected Items(Parcel in) {
        id = in.readInt();
        link = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public Items(int id , String link){
        this.id = id;
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(link);
    }
}
