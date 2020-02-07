package com.example.carbuddy.util;

import android.content.Intent;
import android.os.Bundle;

import com.example.carbuddy.Expense;
import com.example.carbuddy.ExpenseActivity;
import com.example.carbuddy.R;
import com.example.carbuddy.firebase.FirebaseController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import com.example.carbuddy.firebase.FirebaseNotifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AddExpenses extends AppCompatActivity implements  FirebaseNotifier{
 private EditText addCost;
 private EditText addPlace;
 private Button addExpense;
 private FirebaseController firebaseController;
    private int selectedObjectIndex = -1;
    private List<ExpenseFireBase> expenses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addCost = findViewById(R.id.addCost);
        addPlace = findViewById(R.id.addPlace);
        addExpense = findViewById(R.id.addexpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (expenseValid()) {
                    Integer cost = Integer.parseInt(addCost.getText().toString());
                    String place = addPlace.getText().toString();


                    String id = selectedObjectIndex >= 0 ? expenses.get(selectedObjectIndex).getId() : null;
                    ExpenseFireBase expenseFireBase = new ExpenseFireBase(id, cost, place);
                    firebaseController.upsert(expenseFireBase);
                    Intent intent = new Intent(AddExpenses.this, ExpenseActivity.class);
                    startActivity(intent);
              }
            }
        });
        firebaseController = FirebaseController.getInstance(this);
        firebaseController.findAll();
    }




    private boolean expenseValid() {

        if (addCost.getText() == null || addCost.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    R.string.noCostErrExpense,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (addPlace.getText() == null || addPlace.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.toastErrLocation, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public void notifyChangesonExpenses(List<ExpenseFireBase> exnses) {
        addCost.setText(null);
        addPlace.setText(null);
        selectedObjectIndex = -1;
            expenses.clear();
            expenses.addAll(exnses);


    }
}


