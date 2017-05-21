package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tomas on 4/30/2017.
 */

public class CompaniesActivity extends Activity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference brand_ref = databaseReference.child("brands");
    List<String> companies_name_List = new ArrayList<>();
    List<String> companies_id_List = new ArrayList<>();
    ListView comment_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //remove status bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        //setting for a list of banks
        setContentView(R.layout.activity_companies);
        comment_listView = (ListView) findViewById(R.id.companies_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, companies_name_List);
        comment_listView.setAdapter(adapter);

        //onclick listener on a list of banks -> move to selected bank details screen
        comment_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Data.company_name = companies_name_List.get(position);
                Data.company_id = companies_id_List.get(position);
                Intent intent = new Intent(getApplicationContext(), CompanyDetailsActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //set a listener on a firebase databes
        companies_name_List.clear();
        companies_id_List.clear();
        brand_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    companies_id_List.add(postSnapshot.getKey().toString());
                    companies_name_List.add(postSnapshot.getValue(String.class));
                }
                ((BaseAdapter) comment_listView.getAdapter()).notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //disable back button
    @Override
    public void onBackPressed() {
    }

}
