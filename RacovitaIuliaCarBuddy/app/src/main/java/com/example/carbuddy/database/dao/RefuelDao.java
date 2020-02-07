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
public interface RefuelDao {

    @Query("select * from refs")
    List<Refuel> getAllRefuels();


    @Query("select * from refs where userId= :userId order by totalCost DESC")
    List<Refuel> getRefuelByCost(long userId);


    @Query("select * from refs where userId= :userId  order by lit")
    List<Refuel> getRefuelByLit(long userId);



    @Query("select * from refs where userId= :userId ")
    List<Refuel> getRefuel(long userId);


    @Insert
    long insert(Refuel refuel);

    @Update
    int update(Refuel refs);

    @Delete
    void deleteAllRefuels(List<Refuel> refs);


}
