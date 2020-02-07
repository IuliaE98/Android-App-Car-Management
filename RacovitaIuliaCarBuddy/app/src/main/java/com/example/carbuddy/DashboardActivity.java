package com.example.carbuddy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    private Button fuel;
    private Button moreInfo;
    private Button Expense;
    private Button maps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponents();


    }


    public void initComponents(){
        fuel = findViewById(R.id.buttonFueld);
        moreInfo=findViewById(R.id.btnMoreInfod);
        Expense=findViewById(R.id.buttonInvestmentd);
        maps = findViewById(R.id.btnmaps);
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RefuelHistory.class);
                startActivity(intent);
            }
        });

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                Intent intent2 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent2);
            }
        });

        Expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(),ExpenseActivity.class);
                startActivity(intent3);
            }
        });
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent4);
            }
        });
    }
}
