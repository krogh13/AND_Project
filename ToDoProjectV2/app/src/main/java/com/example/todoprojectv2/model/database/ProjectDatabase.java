package com.example.todoprojectv2.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoprojectv2.model.shared.ToDoModelEntity;

@Database(entities = {ToDoModelEntity.class}, version = 1, exportSchema = false)
public abstract class ProjectDatabase extends RoomDatabase {

    private static ProjectDatabase instance;
    public abstract ToDoDAO toDoDAO();

    public static synchronized ProjectDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProjectDatabase.class,"project_database")
                    .fallbackToDestructiveMigration()
                    //todo .addCallback can be deleted when todoFragment is fully running
                    //.addCallback(roomDatabaseCallback)
                    .build();
        }
        return instance;
    }

    //todo delete when the todoFragment is fully running
    /* --- --- --- --- --- --- --- --- Created for populating the database --- --- --- --- --- --- --- --- */

    /*

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDatabaseAsync(instance).execute();
//        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsync(instance).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private ToDoDAO toDoDAO;

        private PopulateDatabaseAsync(ProjectDatabase database) {
            toDoDAO = database.toDoDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            toDoDAO.insert(new ToDoModelEntity("Create FAB", 4));
            toDoDAO.insert(new ToDoModelEntity("Create adding todo fragment", 2));
            return null;
        }


    } */
}
