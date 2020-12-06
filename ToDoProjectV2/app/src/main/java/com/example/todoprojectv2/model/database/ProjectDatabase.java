package com.example.todoprojectv2.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoprojectv2.model.shared.ToDoModelEntity;

@Database(entities = {ToDoModelEntity.class}, version = 3, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {

    private static ProjectDatabase instance;
    public abstract ToDoDAO toDoDAO();

    public static synchronized ProjectDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProjectDatabase.class,"project_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
