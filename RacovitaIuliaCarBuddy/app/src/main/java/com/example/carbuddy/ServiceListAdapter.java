package com.example.carbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServiceListAdapter extends ArrayAdapter<Service> {
    private Context nContext;
    int nResource;

    public ServiceListAdapter(@NonNull Context context, int resource, @NonNull List<Service> objects) {
        super(context, resource, objects);
        nContext = context;
        nResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getNameService();
        String mail = getItem(position).getMailService();
        String phone = getItem(position).getPhoneService();
        String location = getItem(position).getLocationService();
        LayoutInflater inflater = LayoutInflater.from(nContext);
        convertView = inflater.inflate(nResource,parent,false);

        TextView tvname = (TextView) convertView.findViewById(R.id.adaptertxtViewname);
        TextView tvmail = (TextView) convertView.findViewById(R.id.adapterTxtViewMail);
        TextView tvphone = (TextView) convertView.findViewById(R.id.adapterTxtViewPhone);
        TextView tvlocation = (TextView) convertView.findViewById(R.id.adapterTxtViewAddress);

        tvname.setText(name);
        tvmail.setText(mail);
        tvphone.setText(phone);
        tvlocation.setText(location);

        return convertView;

    }
}
