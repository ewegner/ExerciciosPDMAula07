package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SaveLoginActivity extends AppCompatActivity {

    private EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_login);

        Bundle extras = getIntent().getExtras();

        user = (EditText) findViewById(R.id.txtUser);
        pass = (EditText) findViewById(R.id.txtPass);

        user.setText(extras.getString("user"));
        pass.setText(extras.getString("pass"));
    }

    public void save(View view) {
        SharedPreferences userData = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();
        editor.putString("user", user.getText().toString());
        editor.putString("password", pass.getText().toString());
        editor.putInt("logins", 1);
        editor.apply();
        editor.commit();
        super.onBackPressed();
    }
}