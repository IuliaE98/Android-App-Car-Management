package com.example.carbuddy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carbuddy.R;

import java.util.List;

public class AdapterRefuel extends ArrayAdapter<Refuel>{
    private Context context;
    private int resource;
    private List<Refuel> refuels;
    private LayoutInflater layout;


    public AdapterRefuel(@NonNull Context context, int resource, @NonNull List<Refuel> refuels,LayoutInflater layout) {
        super(context, resource, refuels);
        this.context = context;
        this.resource= resource;
        this.refuels = refuels;
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layout.inflate(resource, parent, false);
        Refuel refuel = refuels.get(position);
        if(view == null){
            view = layout.inflate(R.layout.refuel_row, null , true);
        }

        TextView tvdata = view.findViewById(R.id.textViewDataRefuel);
        TextView tvKm = view.findViewById(R.id.textViewKm);
        TextView tvLit = view.findViewById(R.id.textViewLiters);
        TextView tvTotalCost = view.findViewById(R.id.textViewCostTotal);


        refuel = refuels.get(position);
        tvdata.setText(refuel.getData());
        tvKm.setText(refuel.getKm().toString());
        tvLit.setText(refuel.getLit().toString());
        tvTotalCost.setText(refuel.getTotalCost().toString());

       return view;
    }
}
