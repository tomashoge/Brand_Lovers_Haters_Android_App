package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Created by Tomas on 5/2/2017.
 */

public class NewCommentActivity extends Activity {
    boolean likeDislikeSelected=false;
    boolean likeSelected=false;
    ImageButton like_btn ;
    ImageButton dislike_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                startActivity(intent);
            }
        });

        like_btn = (ImageButton)findViewById(R.id.likeButton);
        dislike_btn = (ImageButton)findViewById(R.id.dislikeButton);


    }


    public void onLikeClick(View view) {
        likeSelected=true;
        likeDislikeSelected=true;
        like_btn.setImageResource(R.drawable.like_selected);
        dislike_btn.setImageResource(R.drawable.dislike);
    }

    public void onDislikeClick(View view) {
        likeSelected=false;
        likeDislikeSelected=true;
        like_btn.setImageResource(R.drawable.like);
        dislike_btn.setImageResource(R.drawable.dislike_selected);
    }

    public void cancel_btn(View view) {
        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
        startActivity(intent);
    }

    public void submit_btn(View view) {
        Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
        startActivity(intent);
    }
}
