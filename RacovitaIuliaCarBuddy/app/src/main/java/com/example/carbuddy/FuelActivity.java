package com.example.carbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbuddy.database.DatabaseManager;
import com.example.carbuddy.database.dao.RefuelDao;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.database.model.User;
import com.example.carbuddy.util.Refuel;
import com.example.carbuddy.util.SharedPreferencess;

import java.util.Calendar;

public class FuelActivity extends AppCompatActivity {

    private Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;
    //pentru obtinere data curenta
    int year;
    int month;
    private RefuelDao refuelDao;
    int dayOfMonth;
    Calendar calendar;
    private String fuelTank;
    EditText editextKm;
    EditText editTextLiters;
    EditText editTextTotalCost;
    public static final String ADD_REFUEL_HISTORY = "addRefuelHistory";
    public static final int cod = 400;
    Intent intent;
    SharedPreferencess shPref;
    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);
        shPref = new SharedPreferencess(getApplicationContext());
        refuelDao = Room.databaseBuilder(this, DatabaseManager.class, "iua_db")
                .allowMainThreadQueries()
                .build()
                .getRefuelDao();

        intent = getIntent();
        initComponents();

    }


    private void initComponents(){

        add = findViewById(R.id.btnaddFuel);
        selectDate = findViewById(R.id.btnData);
        date = findViewById(R.id.editTextDataFuel);
        editextKm = findViewById(R.id.ediTextKM);
        editTextLiters = findViewById(R.id.editTextL);
        editTextTotalCost = findViewById(R.id.totalCost);


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extragere data curenta
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                datePickerDialog = new DatePickerDialog(FuelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (valid()){
////                    Refuel refuel = createRefuel();
////                    date.setText("");
////                    editextKm.setText("");
////                    editTextLiters.setText("");
////                    editTextTotalCost.setText("");
////                    refuelDao.insert(refuel);
//
////                    Toast.makeText(getApplicationContext(), R.string.ToastRefuelSuccessfullyAdded, Toast.LENGTH_LONG).show();
////                    finish();
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Refuel refuel = createRefuel();
//                            intent.putExtra(ADD_REFUEL_HISTORY, refuel);
//                            setResult(RESULT_OK, intent);
//                            refuelDao.insert(refuel);
//                            startActivity(new Intent(FuelActivity.this, RefuelHistory.class));
//                        }
//                    }, 1000);
//
//                } else {
//                    Toast.makeText(FuelActivity.this, R.string.err_toast_fueladding, Toast.LENGTH_SHORT).show();
//                }
//
//                }
//            });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid()) {
//                    Refuel refuel = createRefuel();
//                    date.setText("");
//                    editextKm.setText("");
//                    editTextLiters.setText("");
//                    editTextTotalCost.setText("");
//                        //transfer de parametri
////                    intent.putExtra(ADD_REFUEL_HISTORY, refuel);
////                    setResult(RESULT_OK, intent);
////                    Toast.makeText(getApplicationContext(), R.string.ToastRefuelSuccessfullyAdded, Toast.LENGTH_LONG).show();
////                    finish();

                    //handler pentru adaugarea in baza de date
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Refuel refuel1 = createRefuel();
                            refuel1.setUserId(shPref.getUser());
//                            intent.putExtra(ADD_REFUEL_HISTORY, refuel1);
//                            setResult(RESULT_OK, intent);
                            refuelDao.insert(refuel1);
                            startActivity(new Intent(FuelActivity.this, RefuelHistory.class));
                        }
                    }, 1000);

                } else {
                    Toast.makeText(FuelActivity.this, R.string.err_toast_fueladding, Toast.LENGTH_SHORT).show();
                }

            }

        });


    }


    private boolean valid(){
            if(date.getText().toString() ==null || date.getText().toString().trim().isEmpty()){
                Toast.makeText(getApplicationContext(), R.string.ToastErrNoDateChecked,Toast.LENGTH_LONG).show();
                return false;
            }
        if(editextKm.getText().toString() ==null || editextKm.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.ToastErrAddKm,Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextLiters.getText().toString() ==null || editTextLiters.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.ToastErrAddKmm ,Toast.LENGTH_LONG).show();
            return false;
        }
        if(editTextTotalCost.getText().toString() ==null || editTextTotalCost.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.ToastErrAddCost,Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
        }

        private Refuel createRefuel(){
           String  data =  date.getText().toString();
            Integer km = Integer.parseInt(editextKm.getText().toString());
            Double liters = Double.parseDouble(editTextLiters.getText().toString());
            Double fullCost = Double.parseDouble(editTextTotalCost.getText().toString());
            return new Refuel(data,km,liters,fullCost) ;

        }

    }




