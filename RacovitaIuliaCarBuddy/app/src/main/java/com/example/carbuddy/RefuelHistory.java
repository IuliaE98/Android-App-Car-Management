package com.example.carbuddy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.RefuelDao;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.database.model.User;
import com.example.carbuddy.util.AdapterRefuel;
import com.example.carbuddy.util.Refuel;
import com.example.carbuddy.util.SharedPreferencess;

import java.util.ArrayList;
import java.util.List;

public class RefuelHistory extends AppCompatActivity {
    private Button btnFuel;
    private ListView lvHistory;
    private Button buttonClear;
    private DatabaseManager database;
    private Button btnReports;
    private RefuelDao refuelDao;
    private SharedPreferencess sh;
    private List<Refuel> refuels = new ArrayList<>();
    public static final String ADD_REFUEL_HISTORY = "addRefuelHistory";
    public static final int cod = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuelhistory);
       // addInListView();


        sh = new SharedPreferencess(getApplicationContext());


        database = Room.databaseBuilder(this, DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build();


        refuelDao = database.getRefuelDao();


        btnFuel = findViewById(R.id.buttonFuel);
        btnFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Intent intent3 = new Intent(getApplicationContext(), FuelActivity.class);
                startActivityForResult(intent3, cod);
            }
        });
        addInListView1();
        buttonClear = findViewById(R.id.btnClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refuelDao.deleteAllRefuels(refuels);
            }
        });

        btnReports = findViewById(R.id.btnReports);
        btnReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReportActivity.class);
                startActivity(intent);
            }
        });
    }

   //transfer de parametri intre FuelActivity si RefuelHistory
//    private void addInListView() {
//        lvHistory = findViewById(R.id.lvRefuel);
//        lvHistory.invalidate();
//        AdapterRefuel adapter = new AdapterRefuel(getApplicationContext(), R.layout.refuel_row, refuels, getLayoutInflater());
//        lvHistory.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

    //import din baza de date in functie de user
    private void addInListView1() {
        lvHistory = findViewById(R.id.lvRefuel);
        lvHistory.invalidate();
        refuels = refuelDao.getRefuel(sh.getUser());
      // refuels = refuelDao.getAllRefuels();
        AdapterRefuel adapter = new AdapterRefuel(getApplicationContext(), R.layout.refuel_row, refuels, getLayoutInflater());
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
       // addInListView();
      addInListView1();

    }


//@Override
//protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//    if ((this.cod == requestCode) && (resultCode == RESULT_OK) && (data != null)) {
//        Refuel refuel = data.getParcelableExtra(FuelActivity.ADD_REFUEL_HISTORY);
//        if (refuel != null) {
//            refuels.add(refuel);
//          //  Toast.makeText(getApplicationContext(), "Alimentare primita", Toast.LENGTH_LONG).show();
//            // addInListView();
//        }
//    }
//}
}