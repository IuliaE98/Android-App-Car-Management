package com.example.carbuddy;

import android.content.Intent;
import android.os.Bundle;

import com.example.carbuddy.firebase.FirebaseController;
import com.example.carbuddy.firebase.FirebaseNotifier;
import com.example.carbuddy.network.HttpManager;
import com.example.carbuddy.network.HttpResponse;
import com.example.carbuddy.network.JsonParser;
import com.example.carbuddy.util.AdapterExpense;
import com.example.carbuddy.util.AddExpenses;
import com.example.carbuddy.util.ExpenseFireBase;
import com.example.carbuddy.util.Refuel;
import com.google.firebase.database.DataSnapshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity implements FirebaseNotifier{
    private static final String URL = "https://api.myjson.com/bins/qpngy";
    private Button btnShowExpenses;
    private HttpResponse httpResponse;
    private ListView lvHistorry;
    private ListView lvViewFirebase;
    private Button addExpense;
    private Button expenseFirebase;
    private FirebaseController firebaseController;
    private ArrayList<Expense> expenses = new ArrayList<>();
    private List<ExpenseFireBase> expenseFireBases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvHistorry = findViewById(R.id.expenseHistory);
        lvViewFirebase = findViewById(R.id.expensehistoryfirebase);
       btnShowExpenses = findViewById(R.id.buttonShowExpenses);
       btnShowExpenses.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               selectResponse(httpResponse.getExpenses());
           }
       });

        new HttpManager() {
            @Override
            protected void onPostExecute(String s) {
                httpResponse = JsonParser.parseJson(s);

            }
        }.execute(URL);


        addExpense = findViewById(R.id.btnAddExpense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseActivity.this, AddExpenses.class);
                startActivity(intent);
            }
        });

        expenseFirebase = findViewById(R.id.btnExpenseFirebase);
        expenseFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            addInListViewFirebase();
            }
        });
        firebaseController = FirebaseController.getInstance(this);
        firebaseController.findAll();

    }



    private void selectResponse(List<Expense> list){
        for(int i=0;i<list.size();i++){
            if(!expenses.contains(list.get(i)))
                expenses.add(list.get(i));
            AdapterExpense adapterExpense = new AdapterExpense(getApplicationContext(),R.layout.expense_row,expenses,getLayoutInflater());
            lvHistorry.setAdapter(adapterExpense);
            adapterExpense.notifyDataSetChanged();
        }

    }

    @Override
    public void notifyChangesonExpenses(List<ExpenseFireBase> expens) {
        expenseFireBases.addAll(expens);
    }

    private void addInListViewFirebase() {

        lvViewFirebase.invalidate();

        ArrayAdapter<ExpenseFireBase> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, expenseFireBases);

        lvViewFirebase.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();

    }
}
