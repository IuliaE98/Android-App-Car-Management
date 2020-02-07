package com.example.carbuddy;

import android.os.Bundle;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.RefuelDao;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.util.Refuel;
import com.example.carbuddy.util.SharedPreferencess;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Database;
import androidx.room.Room;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class ReportActivity extends AppCompatActivity {
private RefuelDao refuelDao;
private UserDao userDao;
private ListView lvCost;
private ListView lvLiters;
private Button btnCost;
private FileOutputStream fileOutputStream;
private Button btnLiters;
private Button btndwnldCost;
private Button btndwnldLiters;
SharedPreferencess sh;
DatabaseManager database;
private List<Refuel> refuelsCost = new ArrayList<>();
private List<Refuel> refuelsLiters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnCost = findViewById(R.id.Report2);
        btnLiters = findViewById(R.id.Report1);
        btndwnldCost = findViewById(R.id.btndwnlodCost);
        btndwnldLiters = findViewById(R.id.btndwnlodlit);

        sh = new SharedPreferencess(getApplicationContext());


        database = Room.databaseBuilder(this, DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build();


        refuelDao = database.getRefuelDao();
        btnCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addinListViewCost();
            }
        });

        btnLiters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addInListViewLit();
            }
        });
        btndwnldCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOut= null;
                try {
                    fileOut = openFileOutput("ReportCost.txt", MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
                try {
                    outputWriter.write(refuelsCost.toString());
                    outputWriter.write("\n");
                    outputWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        btndwnldLiters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOut= null; //
                try {
                    fileOut = openFileOutput("ReportLister.txt", MODE_APPEND);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
                try {
                    outputWriter.write(refuelsLiters.toString());
                    outputWriter.write("\n");
                    outputWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //import din baza de date in functie de user
    private void addInListViewLit() {
        lvLiters = findViewById(R.id.recicleViewLiters);
        lvLiters.invalidate();
        refuelsLiters = refuelDao.getRefuelByLit(sh.getUser());
        ArrayAdapter<Refuel> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, refuelsLiters);

        lvLiters.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();

    }
    private void addinListViewCost() {
        lvCost = findViewById(R.id.recicleViewCost);
        lvCost.invalidate();
        refuelsCost = refuelDao.getRefuelByCost(sh.getUser());
        ArrayAdapter<Refuel> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, refuelsCost);

        lvCost.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();

    }

}
