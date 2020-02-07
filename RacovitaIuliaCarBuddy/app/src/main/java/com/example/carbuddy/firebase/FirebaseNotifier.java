package com.example.carbuddy.firebase;

import com.example.carbuddy.Expense;
import com.example.carbuddy.util.ExpenseFireBase;

import java.util.List;

public interface FirebaseNotifier {

    void notifyChangesonExpenses(List<ExpenseFireBase> expenses);

}
