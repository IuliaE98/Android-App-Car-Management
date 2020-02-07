package com.example.carbuddy.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carbuddy.Expense;
import com.example.carbuddy.R;

import java.util.List;

public class AdapterExpense extends ArrayAdapter<Expense> {
    private Context context;
    private int resource;
    private List<Expense> expenses;
    private LayoutInflater layout;

    public AdapterExpense(Context context,
                         int resource,
                         List<Expense> expenses,
                         LayoutInflater layout) {
        super(context, resource, expenses);
        this.context = context;
        this.resource = resource;
        this.expenses = expenses;
        this.layout = layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layout.inflate(resource, parent, false);
        Expense expense = expenses.get(position);
        if(view == null){
            view = layout.inflate(R.layout.expense_row, null , true);
        }

        TextView tvCost = view.findViewById(R.id.txtViewprice);
        TextView tvPlace = view.findViewById(R.id.txtViewplace);

        expense = expenses.get(position);
        tvCost.setText(expense.getCost().toString());
        tvPlace.setText(expense.getPlace().toString());

        return view;
    }
}
