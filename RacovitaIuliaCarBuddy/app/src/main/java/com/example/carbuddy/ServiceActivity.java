package com.example.carbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ListView listView = findViewById(R.id.serviceListView);
            //create Service objects
        Service ContactAuto = new Service("ContactAutoService","Fabrica de chibrituri 24-26, București, sector 5","office@contactautoservice.ro","0755148861");
        Service AutoErebus = new Service("AutoErebus","Str. Parcului nr. 2, Sector 1, Bucuresti","office@autoerebus.ro","0212223481");
        Service AutoK9 = new Service("AutoK9","Sos. Vitan Barzesti, nr. 5-7, Sector 4, Bucuresti, 042121","office@autok9.ro","0213173737");
        Service ConceptCarService = new Service("ConceptCarService","Bld Timișoara 76, București","office@conceptcarservice.ro","0760654463");

        ArrayList<Service> serviceList = new ArrayList<>();
        serviceList.add(ContactAuto);
        serviceList.add(AutoErebus);
        serviceList.add(AutoK9);
        serviceList.add(ConceptCarService);

        ServiceListAdapter adapter = new ServiceListAdapter(this, R.layout.service_row,serviceList);
        listView.setAdapter(adapter);

    }
}
