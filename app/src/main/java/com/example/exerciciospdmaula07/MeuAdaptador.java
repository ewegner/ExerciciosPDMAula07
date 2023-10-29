package com.example.exerciciospdmaula07;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

public class MeuAdaptador extends SimpleAdapter {
    public MeuAdaptador(Context ctx, List<Map<String, Object>> lista, int umaLinha, String[] de, int[] para) {
        super(ctx, lista, umaLinha, de, para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView idCar  = (TextView) v.findViewById(R.id.txtId);
        TextView modelCar   = (TextView) v.findViewById(R.id.txtModelo);
        TextView yearCar   = (TextView) v.findViewById(R.id.txtAno);
        TextView valueCar  = (TextView) v.findViewById(R.id.txtValor);
        if (position % 2 == 0) {
            v.setBackgroundColor(Color.parseColor("#CCCCCC"));

            idCar.setTextColor(Color.parseColor("#000000"));
            modelCar.setTextColor(Color.parseColor("#000000"));
            yearCar.setTextColor(Color.parseColor("#000000"));
            valueCar.setTextColor(Color.parseColor("#000000"));
        } else {
            v.setBackgroundColor(Color.parseColor("#FFFFFF"));

            idCar.setTextColor(Color.parseColor("#000000"));
            modelCar.setTextColor(Color.parseColor("#000000"));
            yearCar.setTextColor(Color.parseColor("#000000"));
            valueCar.setTextColor(Color.parseColor("#000000"));
        }

        return v;
    }
}
