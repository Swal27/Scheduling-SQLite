package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<List> arrayList;
    public ListAdapter(Context context, ArrayList<List> arrayList)
    {
        this.context=context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.listadapt,null);
           TextView matkulvt = (TextView)convertView.findViewById(R.id.matkulvt);
           TextView jamvt = (TextView)convertView.findViewById(R.id.jamvt);
           TextView dosenvt = (TextView)convertView.findViewById(R.id.dosenvt);
           TextView asistenvt = (TextView)convertView.findViewById(R.id.asistenvt);
           TextView labvt = (TextView)convertView.findViewById(R.id.labvt);
           TextView jurusanvt = (TextView)convertView.findViewById(R.id.jurusanvt);
           TextView idtv = (TextView)convertView.findViewById(R.id.idtv);

           List list = arrayList.get(position);

           matkulvt.setText(list.getMatkul());
           jamvt.setText(list.getJamulai() + "-" + list.getJamakhir());
           dosenvt.setText("Dosen   :" + list.getDosen());
           asistenvt.setText("Asisten   :" + list.getAsisten());
           labvt.setText("Lab " + list.getLab());
           jurusanvt.setText(list.getJurusan());
           idtv.setText(toString().valueOf(list.getId()));

            notifyDataSetChanged();

        return convertView;
    }


}

