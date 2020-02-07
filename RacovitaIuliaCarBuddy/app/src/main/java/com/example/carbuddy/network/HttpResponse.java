package com.example.carbuddy.network;

import com.example.carbuddy.Expense;

import java.util.List;

public class HttpResponse {
    public List<Expense> expenses;


    public HttpResponse(List<Expense> expenses) {
        this.expenses = expenses;

    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }



    @Override
    public String toString() {
        return "HttpResponse{" +
                "expenses=" + expenses +
                '}';
    }


}
