package com.example.carbuddy.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.carbuddy.Expense;
import com.example.carbuddy.util.ExpenseFireBase;
import com.example.carbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseController {
    private static final String EXPENSE_TABLE_NAME = "expenseslist";
    private DatabaseReference database;
    private FirebaseDatabase controller;
    private FirebaseNotifier firebaseNotifier;
    private static FirebaseController firebaseController;

    private FirebaseController(FirebaseNotifier firebaseNotifier) {
        controller = FirebaseDatabase.getInstance();
        this.firebaseNotifier = firebaseNotifier;
    }

    private void open() {
        database = controller.getReference(EXPENSE_TABLE_NAME);
    }

    public static FirebaseController getInstance(FirebaseNotifier firebaseNotifier) {
        if (firebaseController == null) {
            synchronized (FirebaseController.class) {
                if (firebaseController == null) {
                    firebaseController = new FirebaseController(firebaseNotifier);
                }
            }
        }
        return firebaseController;
    }

    public String upsert(final ExpenseFireBase expenseFireBase) {
        if (expenseFireBase == null) {
            return null;
        }
        open();

        //insert
        if (expenseFireBase.getId() == null || expenseFireBase.getId().trim().isEmpty()) {
            //adaug o noua inregistrare in Firebase
            expenseFireBase.setId(database.push().getKey());
        }

        //update
        database.child(expenseFireBase.getId()).setValue(expenseFireBase);
        database.child(expenseFireBase.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ExpenseFireBase temp = dataSnapshot.getValue(ExpenseFireBase.class);
                if (temp != null) {
                    Log.i("FireController", "Expense is updated " + temp.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseController", "Expense is not saved");
            }
        });
        return expenseFireBase.getId();
    }

    //stergerea unei inregistrari
    public void delete(final ExpenseFireBase expenseFireBase) {
        if (expenseFireBase == null || expenseFireBase.getId() == null || expenseFireBase.getId().trim().isEmpty()) {
            return;
        }
        open();

        database.child(expenseFireBase.getId()).removeValue();

        database.child(expenseFireBase.getId()).removeEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FirebaseController",
                       "Expense with id" + expenseFireBase.getId() + " is removed!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("FirebaseController", "Remove is not working");
            }
        });
    }

    public void findAll() {
        open();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<ExpenseFireBase> expenseFireBases = new ArrayList<>();
                //parcurgem toate randurile din parinte
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ExpenseFireBase expense = data.getValue(ExpenseFireBase.class);
                    if (expense != null) {
                        expenseFireBases.add(expense);
                    }
                }
                firebaseNotifier.notifyChangesonExpenses(expenseFireBases);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ReportActivity", "Data is not available");
            }
        });
    }

}
