package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    TextView user, numberSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        Bundle extras = getIntent().getExtras();

        user = (TextView) findViewById(R.id.txtUser);
        numberSessions = (TextView) findViewById(R.id.txtNumber);

        int number = Integer.parseInt(extras.getString("numberSessions")) + 1;

        SharedPreferences userData = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();
        editor.putInt("logins", number);
        editor.apply();
        editor.commit();

        user.setText(extras.getString("user"));
        numberSessions.setText(String.valueOf(number));
    }

    public void logout(View view) {
        SharedPreferences userData = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();
        editor.putString("user", "");
        editor.putString("password", "");
        editor.putInt("logins", 0);
        editor.apply();
        editor.commit();
        super.onBackPressed();
    }

    public void gotoEx1(View view) {
        Intent intent = new Intent(LoggedInActivity.this, CarsActivity.class);
        startActivity(intent);
    }
}