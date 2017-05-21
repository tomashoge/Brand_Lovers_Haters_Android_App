package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 5/2/2017.
 */


public class CompanyDetailsActivity extends Activity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dbR_brand_details_list = databaseReference.child("brands_details");
    TextView likes_tw;
    TextView dislikes_tw;
    List<String> company_comments_list = new ArrayList<>();
    ListView comment_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        //set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompaniesActivity.class);
                startActivity(intent);
            }
        });
        //set tab name
        TextView name_tw = (TextView) findViewById(R.id.companyDetails_textView);
        name_tw.setText(Data.company_name);
        //set likes/dislikes
        likes_tw = (TextView) findViewById(R.id.textView_likes);
        dislikes_tw = (TextView) findViewById(R.id.textView_dislikes);
        //setting of comment list
        comment_listView = (ListView) findViewById(R.id.comment_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, company_comments_list);
        comment_listView.setAdapter(adapter);

    }

    //move to a adding comment screen
    public void addCommentClick(View view) {
        Intent intent = new Intent(getApplicationContext(), NewCommentActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbR_brand_details_list.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get details of a selected bank and set values on a screen
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.getKey().toString().equals(Data.company_id)) {
                        dislikes_tw.setText(postSnapshot.child("dislike").getValue().toString());
                        likes_tw.setText(postSnapshot.child("like").getValue().toString());

                        for (DataSnapshot commentSnapshot : postSnapshot.child("comments").getChildren()) {
                            company_comments_list.add(commentSnapshot.child("comment").getValue(String.class));
                        }
                        ((BaseAdapter) comment_listView.getAdapter()).notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



