package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 5/2/2017.
 */

public class NewCommentActivity extends Activity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbR_brand_details_list = databaseReference.child("brands_details");
    DatabaseReference dbR_brand = dbR_brand_details_list.child(Data.company_id);

    boolean likeDislikeSelected = false;
    boolean likeSelected = false;
    ImageButton like_btn;
    ImageButton dislike_btn;
    EditText comment_ed;
    long nLikes;
    long nDislikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        //set a toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                startActivity(intent);
            }
        });
        like_btn = (ImageButton) findViewById(R.id.likeButton);
        dislike_btn = (ImageButton) findViewById(R.id.dislikeButton);
        comment_ed = (EditText) findViewById(R.id.comment_editText);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //lget a number of likes/dislikes to update them after adding comment
        dbR_brand.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nLikes = dataSnapshot.child("like").getValue(Long.class);
                nDislikes = dataSnapshot.child("dislike").getValue(Long.class);
                ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onLikeClick(View view) {
        likeSelected = true;
        likeDislikeSelected = true;
        like_btn.setImageResource(R.drawable.like_selected);
        dislike_btn.setImageResource(R.drawable.dislike);
    }

    public void onDislikeClick(View view) {
        likeSelected = false;
        likeDislikeSelected = true;
        like_btn.setImageResource(R.drawable.like);
        dislike_btn.setImageResource(R.drawable.dislike_selected);
    }

    //return to company screen
    public void cancel_btn(View view) {
        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
        startActivity(intent);
    }

    //send a comment to a databese
    public void submit_btn(View view) {
        //chcek if everything is filled
        if (!likeDislikeSelected) {
            Toast.makeText(this, R.string.like_dislike_unselected, Toast.LENGTH_SHORT).show();
            return;
        }
        String commentString = "";
        if (comment_ed.getText() == null || (commentString = comment_ed.getText().toString()).isEmpty()) {
            Toast.makeText(this, R.string.comment_is_missing, Toast.LENGTH_SHORT).show();
            return;
        }

        //add to like counter
        if (likeSelected) {
            dbR_brand.child("like").setValue(nLikes + 1);
        } else {
            dbR_brand.child("dislike").setValue(nDislikes + 1);
        }

        //add comment
        Comment comment = new Comment(new SimpleDateFormat("yyyyMMdd").format(new Date()), likeSelected, Data.user_id, commentString);
        DatabaseReference dbR_brand_comments = dbR_brand.child("comments");
        String key = dbR_brand_comments.push().getKey();
        dbR_brand_comments.child(key).setValue(comment);
        Toast.makeText(this, R.string.comment_added, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
        startActivity(intent);
    }
}
