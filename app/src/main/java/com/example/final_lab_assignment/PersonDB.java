package com.example.final_lab_assignment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

@Database(entities = Person.class,exportSchema = false,version = 1)
public abstract class PersonDB extends RoomDatabase
{
    public static final String DB_NAME = "user_db";

    private static PersonDB personDB;

   public static PersonDB getInstance(Context context)
   {
       if(personDB == null)
       {
           personDB = Room.databaseBuilder(context.getApplicationContext(), PersonDB.class,PersonDB.DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
       }
       return personDB;
   }
    public abstract LocationDao daoObjct();

}
