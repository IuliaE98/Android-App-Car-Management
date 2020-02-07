package com.example.carbuddy;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable {

    private Integer cost;
    private String place;
    private ExpenseDetails expenseDetails;


    public Expense(Integer cost, String place) {
        this.cost = cost;
        this.place = place;
    }


    public Expense(Integer cost, String place, ExpenseDetails expenseDetails) {
        this.cost = cost;
        this.place = place;
        this.expenseDetails = expenseDetails;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[0];
        }
    };

    public ExpenseDetails getExpenseDetails() {
        return expenseDetails;
    }

    public void setExpenseDetails(ExpenseDetails expenseDetails) {
        this.expenseDetails = expenseDetails;
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



    @Override
    public String toString() {
        return "Expense{" +
                "cost=" + cost +
                ", place='" + place + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

            parcel.writeInt(cost);
            parcel.writeString(place);
        }

    private Expense(Parcel in) {
        this.cost = in.readInt();
        this.place = in.readString();
    }

    }
