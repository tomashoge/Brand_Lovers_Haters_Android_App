package com.example.tomas.pda_app;

/**
 * Created by Tomas on 5/21/2017.
 */

//class for comment to be sent to a firebase
public class Comment {
    String Date;
    boolean like;
    String author;
    String comment;

    public Comment(String date, boolean like, String author, String comment) {
        Date = date;
        this.like = like;
        this.author = author;
        this.comment = comment;
    }
}
