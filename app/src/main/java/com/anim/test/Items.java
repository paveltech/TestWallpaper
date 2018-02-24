package com.anim.test;

/**
 * Created by lolipop on 2/25/2018.
 */

public class Items {

    public int id;
    public String link;

    public Items(){

    }


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

}
