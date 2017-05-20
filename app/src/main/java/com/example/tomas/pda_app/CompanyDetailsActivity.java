package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Tomas on 5/2/2017.
 */


public class CompanyDetailsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_company_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompaniesActivity.class);
                startActivity(intent);
            }
        });

        String[] comments = new String[]{
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment",
                "comment comment comment comment comment comment comment comment comment comment comment comment"
        };


        ListView listView = (ListView) findViewById(R.id.comment_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, comments);

        listView.setAdapter(adapter);


    }


    public void addCommentClick(View view) {
        Intent intent = new Intent(getApplicationContext(), NewCommentActivity.class);
        startActivity(intent);
    }
}



