package com.mirmahfuz.simpleblog;

/**
 * Created by mirmahfuz on 6/22/17.
 */

public class Blog {

    private String title;
    private String des;
    private String image;
    private String username;


    public Blog(){

    }



    public Blog(String title, String des , String image){
        this.title = title;
        this.des = des;
        this.image = image;
        this.username = username;

    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
