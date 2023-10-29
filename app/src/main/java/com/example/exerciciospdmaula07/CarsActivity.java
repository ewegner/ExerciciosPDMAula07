package com.example.exerciciospdmaula07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarsActivity extends AppCompatActivity {

    private DatabaseHelper helper;

    private EditText carYear, carModel;

    private ListView carList;

    List<Map<String,Object>> carros;

    String [] de = {"id", "modelo", "ano", "valor"};

    int [] para = {R.id.txtId, R.id.txtModelo, R.id.txtAno, R.id.txtValor};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        carYear    = (EditText) findViewById(R.id.ano);
        carModel   = (EditText) findViewById(R.id.modelo);
        carList = (ListView) findViewById(R.id.carsList);

        helper = new DatabaseHelper(this);

        carList.setClickable(true);
        carList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String idDados = String.valueOf(carros.get(position).get("id"));
                Intent intent = new Intent(CarsActivity.this, UpdateActivity.class);
                intent.putExtra("idDados", idDados);
                startActivity(intent);
            }
        });

    }

    public void add(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        String ano = carYear.getText().toString();
        String modelo = carModel.getText().toString();
        String query = "";

        if (ano.isEmpty() && modelo.isEmpty()) {
            query = "SELECT * FROM carros";
        } else if (!ano.isEmpty() && modelo.isEmpty()) {
            query = "SELECT * FROM carros WHERE ano = " + ano;
        } else if (ano.isEmpty() && !modelo.isEmpty()) {
            query = "SELECT * FROM carros WHERE modelo LIKE '%" + modelo + "%' ORDER BY ano DESC";
        } else {
            query = "SELECT * FROM carros WHERE ano = " + ano + " AND modelo LIKE '" + modelo + "' ORDER BY ano DESC";
        }

        carros = listarCarros (query);

        SimpleAdapter adapter;

        adapter = new MeuAdaptador(CarsActivity.this, carros, R.layout.uma_linha, de, para);
        carList.setAdapter(adapter);
    }

    private List<Map<String, Object>> listarCarros(String query) {
        carros = new ArrayList<>();
        Map<String,Object> titles = new HashMap<>();
        titles.put(de[0], "ID");
        titles.put(de[1], "Modelo");
        titles.put(de[2], "Ano");
        titles.put(de[3], "Valor");
        carros.add(titles);

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            String id = cursor.getString(0);
            String modelo = cursor.getString(1);
            int ano = cursor.getInt(2);
            double valor = cursor.getDouble(3);
            item.put(de[0], id);
            item.put(de[1], modelo);
            item.put(de[2], ano);
            item.put(de[3], String.format("R$ %.2f", valor));
            carros.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return carros;
    }

    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}