package com.example.project0.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface TestDao
{
    @Insert
    Completable insertTest(Test test);

    @Query("select * from test_table")
    Observable<List<Test>> getTests();
}
