package com.germanlizondo.sudokuuf1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.germanlizondo.sudokuuf1.Model.User;

import java.util.ArrayList;

public class PuntuacionesAdapter extends BaseAdapter {

    private ArrayList<User> juggadores;
    protected Activity activity;

    public PuntuacionesAdapter(Activity activity,ArrayList<User> juggadores) {
        this.juggadores = juggadores;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.juggadores.size();
    }

    @Override
    public Object getItem(int i) {
        return this.juggadores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.puntuaciones_item, null);
        }

        User user = juggadores.get(i);

        TextView nombreUser = (TextView)view.findViewById(R.id.nombreUser);
        nombreUser.setText(user.getNom());

        TextView puntuacionesUser = (TextView)view.findViewById(R.id.puntuacionesUser);
        puntuacionesUser.setText(user.getPunts()+"");

        return view;
    }
}
