package com.example.tomas.pda_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view) {
        EditText userName = (EditText) findViewById(R.id.user_name);
        EditText userPassword = (EditText) findViewById(R.id.password);
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();

        Intent intent = new Intent(getApplicationContext(), CompaniesActivity.class);
        startActivity(intent);


    }
}
