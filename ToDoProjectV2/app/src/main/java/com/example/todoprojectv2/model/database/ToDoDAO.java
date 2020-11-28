package com.example.todoprojectv2.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.List;

@Dao
public interface ToDoDAO {

    @Query("SELECT * FROM todo_table ORDER BY Priority DESC")
    LiveData<List<ToDoModelEntity>> getAllToDos();

    @Query("DELETE FROM todo_table")
    void deleteAllToDos();

    @Insert
    void insert(ToDoModelEntity toDoEntity);

    @Update
    void update(ToDoModelEntity toDoEntity);

    @Delete
    void delete(ToDoModelEntity toDoEntity);
}
