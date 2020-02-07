package com.example.carbuddy.database;

import android.content.Context;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.carbuddy.database.dao.RefuelDao;
import com.example.carbuddy.database.model.User;
import com.example.carbuddy.database.dao.UserDao;
import com.example.carbuddy.util.Refuel;


@Database(entities = {User.class,Refuel.class},
        exportSchema = false,
        version = 1)



public abstract class DatabaseManager
        extends RoomDatabase {
    private static final String DB_NAME = "iua_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(
            Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room
                            .databaseBuilder(context,
                                    DatabaseManager.class,
                                    DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }

    public abstract UserDao getUserDao();
    public abstract RefuelDao getRefuelDao();


}
