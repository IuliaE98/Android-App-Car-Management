package com.example.carbuddy.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.carbuddy.database.model.User;
import com.example.carbuddy.util.Refuel;


import java.util.List;

@Dao
public interface UserDao {


    @Query("select * from users")
    List<User> getAll();

    @Query("select * from users where email= :email and parola= :parola")
            User getUser(String email, String parola);

    @Query("select * from users where id= :id ")
    User getUserById(long id);


    @Insert
    long insert(User user);


    @Update
    int update(User users);

    @Delete
    void delete(User user);

}
