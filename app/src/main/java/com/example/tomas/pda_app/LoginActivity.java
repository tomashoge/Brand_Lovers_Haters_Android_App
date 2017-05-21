package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//first screen: for login
public class LoginActivity extends Activity {

    EditText user_name_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name_et = (EditText) findViewById(R.id.user_name);
    }

    public void onLoginClick(View view) {
        String commentString = "";
        if (user_name_et.getText() == null || (Data.user_id = user_name_et.getText().toString()).isEmpty()) {
            Toast.makeText(this, R.string.email_not_filled, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), CompaniesActivity.class);
        startActivity(intent);
    }

    //disable return button
    @Override
    public void onBackPressed() {
    }
}
