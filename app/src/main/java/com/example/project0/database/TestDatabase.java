package com.example.project0.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Test.class, version = 1)
public abstract class TestDatabase extends RoomDatabase
{
    private static TestDatabase instance;
    public abstract TestDao testDao();

    public static synchronized TestDatabase getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,TestDatabase.class, "test_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
