package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText modelo, valor, ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        modelo = (EditText) findViewById(R.id.modelo);
        ano    = (EditText) findViewById(R.id.ano);
        valor  = (EditText) findViewById(R.id.valor);

        helper = new DatabaseHelper(this);
    }

    public void save (View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", ano.getText().toString());
        values.put("valor", valor.getText().toString());

        long resultado = db.insert("carros", null, values);

        if (resultado != -1) {
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, CarsActivity.class);
        startActivity(intent);
    }

    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}