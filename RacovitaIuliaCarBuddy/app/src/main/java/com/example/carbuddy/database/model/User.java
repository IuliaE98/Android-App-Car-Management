package com.example.carbuddy.database.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.carbuddy.util.Refuel;

import java.util.Date;
import java.util.List;


@Entity(tableName = "users")
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "prenume")
    private String prenume;
    @ColumnInfo(name = "nume")
    private String nume;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "parola")
    private String parola;
    @ColumnInfo(name = "modelmasina")
    private String modelmasina;
    @ColumnInfo(name = "sex")
    private String sex;


    public User(long id, String prenume, String nume, String email, String parola, String modelmasina, String sex) {
        this.id = id;
        this.prenume = prenume;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.modelmasina = modelmasina;
        this.sex = sex;
    }

    @Ignore
    public User(String prenume, String nume, String email, String parola, String modelmasina, String sex) {
        this.prenume = prenume;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.modelmasina = modelmasina;
        this.sex = sex;
    }


    private User(Parcel in) {
        prenume = in.readString();
        nume = in.readString();
        email = in.readString();
        parola = in.readString();
        modelmasina = in.readString();
        sex = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prenume);
        dest.writeString(nume);
        dest.writeString(email);
        dest.writeString(parola);
        dest.writeString(modelmasina);
        dest.writeString(sex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getModelmasina() {
        return modelmasina;
    }

    public void setModelmasina(String modelmasina) {
        this.modelmasina = modelmasina;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "User{" +
                "prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                ", modelmasina='" + modelmasina + '\'' +
                ", sex=" + sex +
                '}';
    }


}
