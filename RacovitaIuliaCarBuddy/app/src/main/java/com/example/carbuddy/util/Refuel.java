package com.example.carbuddy.util;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.example.carbuddy.database.model.User;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "refs",
        foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE))
public class Refuel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "lit")
    private double lit;
    @ColumnInfo(name = "km")
    private Integer km;
    @ColumnInfo(name = "totalCost")
    private double totalCost;

    @ColumnInfo(name="userId")
    private long userId;



    public Refuel( long id, String data,Integer km, double lit, double totalCost,long userId) {
        this.id = id;
        this.data = data;
        this.lit = lit;
        this.km = km;
        this.totalCost = totalCost;
        this.userId = userId;


    }

    @Ignore
    public Refuel(String data,Integer km, double lit, double totalCost) {
        this.data = data;
        this.lit = lit;
        this.km = km;
        this.totalCost = totalCost;

    }



    private Refuel (Parcel in){
        this.data = in.readString();
        this.km = in.readInt();
        this.lit = in.readDouble();
        this.totalCost = in.readDouble();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Refuel{" +
                "data='" + data + '\'' +
                ", km =" + km +
                ", lit=" + lit +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
     dest.writeString(data);
     dest.writeInt(km);
        dest.writeDouble(lit);
     dest.writeDouble(totalCost);


    }

    public static Creator<Refuel> CREATOR = new Creator<Refuel>() {
        @Override
        public Refuel createFromParcel(Parcel source) {
            return new Refuel(source);
        }

        @Override
        public Refuel[] newArray(int size) {
            return new Refuel[size];
        }
    };


    public static Creator<Refuel> getCREATOR() {
        return CREATOR;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Integer getKm(){
        return km;
    }

    public void setKm(Integer km){
        this.km=km;
    }

    public Double getLit() {
        return lit;
    }

    public void setLit(double lit) {
        this.lit = lit;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
