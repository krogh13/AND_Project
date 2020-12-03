package com.example.todoprojectv2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoprojectv2.model.ProjectRepository;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.Date;
import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ProjectRepository repository;
    private LiveData<List<ToDoModelEntity>> allToDos;

    private ToDoModelEntity clickTodo;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allToDos = repository.getAllToDos();
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
        return allToDos;
    }

}
