package com.example.todoprojectv2.stickynotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoprojectv2.model.ProjectRepository;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.Date;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private ProjectRepository repository;
    private LiveData<List<ToDoModelEntity>> allTodos;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allTodos = repository.getAllToDos();
    }

    public void insert(String title, int priority, Date date, String description) {
        repository.insert(new ToDoModelEntity(title, priority, date, description));
    }

    public void update(ToDoModelEntity toDoModelEntity) {
        repository.update(toDoModelEntity);
    }

    public void delete(ToDoModelEntity toDoModelEntity) {
        repository.delete(toDoModelEntity);
    }

    public LiveData<List<ToDoModelEntity>> getAllToDos() {
        return allTodos;
    }

}
