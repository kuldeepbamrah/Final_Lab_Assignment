package com.example.final_lab_assignment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDao
{

    @Insert
    void insert(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person person);

    @Query("Select * from person")
    List<Person> getDefault();
    @Query("Select * from person")
    LiveData<List<Person>> getLiveDefault();




}