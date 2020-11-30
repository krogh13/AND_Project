package com.example.todoprojectv2.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoprojectv2.model.database.ProjectDatabase;
import com.example.todoprojectv2.model.database.ToDoDAO;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.List;

public class ProjectRepository {

    private ToDoDAO toDoDAO;
    private LiveData<List<ToDoModelEntity>> allToDos;

    public ProjectRepository(Application application) {
        ProjectDatabase database = ProjectDatabase.getInstance(application);
        toDoDAO = database.toDoDAO();
        allToDos = toDoDAO.getAllToDos();
    }

    public void insert(ToDoModelEntity toDoModelEntity) {
        new InsertToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class InsertToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private InsertToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.insert(toDoModelEntities[0]);
            return null;
        }
    }

    public void update(ToDoModelEntity toDoModelEntity) {
        new UpdateToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class UpdateToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private UpdateToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.update(toDoModelEntities[0]);
            return null;
        }
    }

    public void delete(ToDoModelEntity toDoModelEntity) {
        new DeleteToDoAsync(toDoDAO).execute(toDoModelEntity);
    }

    private static class DeleteToDoAsync extends AsyncTask<ToDoModelEntity, Void, Void> {

        private ToDoDAO toDoDAO;

        private DeleteToDoAsync(ToDoDAO toDoDAO) {
            this.toDoDAO = toDoDAO;
        }

        @Override
        protected Void doInBackground(ToDoModelEntity... toDoModelEntities) {
            toDoDAO.delete(toDoModelEntities[0]);
            return null;
        }
    }

    public LiveData<List<ToDoModelEntity>> getAllToDos() {
        return allToDos;
    }
}
