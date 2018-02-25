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


    public Items(String link){
        this.link = link;
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

    @Override
    public boolean equals(final Object obj) {

        // If passed object is an instance of Blacklist, then compare the phone numbers, else return false as they are not equal
        if (obj.getClass().isInstance(new Items())) {
            // Cast the object to Blacklist
            final Items bl = (Items) obj;

            // Compare whether the phone numbers are same, if yes, it defines the objects are equal
            if (bl.link.equalsIgnoreCase(this.link))
                return true;
        }
        return false;
    }

}
