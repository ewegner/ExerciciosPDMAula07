package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    String idDados = "";
    private DatabaseHelper helper;
    private EditText modelo, valor, ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Bundle extras = getIntent().getExtras();
        idDados = extras.getString("idDados");

        modelo = (EditText) findViewById(R.id.modelo);
        valor  = (EditText) findViewById(R.id.valor);
        ano    = (EditText) findViewById(R.id.ano);

        helper = new DatabaseHelper(this);
        preencheCampos();
    }

    private void preencheCampos() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String query = "SELECT * FROM carros WHERE id = " + idDados;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        modelo.setText(cursor.getString(1));
        valor.setText(String.valueOf(cursor.getDouble(3)));
        ano.setText(String.valueOf(cursor.getInt(2)));
        cursor.close();
    }

    public void delete(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where [] = new String[] {idDados};

        long resultado = db.delete("carros", "id = ?", where);

        if (resultado != -1) {
            Toast.makeText(this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        String where [] = new String[] {idDados};
        long resultado = db.update("carros", values, "id = ?", where);

        if (resultado != -1) {
            Toast.makeText(this, "Registro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}