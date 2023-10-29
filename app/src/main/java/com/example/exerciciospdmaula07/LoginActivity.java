package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        SharedPreferences userData = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String user = userData.getString("user", "");
        String pass = userData.getString("password", "");
        int numberSessions = userData.getInt("logins", 0);

        EditText usuario = findViewById(R.id.txtUser);
        EditText senha = findViewById(R.id.txtPass);

        if (user.isEmpty() || pass.isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, SaveLoginActivity.class);
            intent.putExtra("user", usuario.getText().toString());
            intent.putExtra("pass", senha.getText().toString());
            startActivity(intent);
        } else if ((!usuario.getText().toString().equals(user)) || (!senha.getText().toString().equals(pass))) {
            Intent intent = new Intent(LoginActivity.this, SaveLoginActivity.class);
            intent.putExtra("user", usuario.getText().toString());
            intent.putExtra("pass", senha.getText().toString());
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoginActivity.this, LoggedInActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("numberSessions", String.valueOf(numberSessions));
            startActivity(intent);
        }
    }
}