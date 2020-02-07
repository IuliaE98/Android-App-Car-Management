package com.example.carbuddy.util;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.carbuddy.Expense;
import com.example.carbuddy.ExpenseDetails;

import java.io.Serializable;

public class ExpenseFireBase implements Serializable {

    private String id;
    private Integer cost;
    private String place;

    public ExpenseFireBase() {
    }

    public ExpenseFireBase(Integer cost, String place) {
        this.cost = cost;
        this.place = place;
    }

    public ExpenseFireBase(String id, Integer cost, String place) {
        this.id = id;
        this.cost = cost;
        this.place = place;
    }



    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "cost=" + cost +
                ", place='" + place + '\'' +
                '}';
    }



}
