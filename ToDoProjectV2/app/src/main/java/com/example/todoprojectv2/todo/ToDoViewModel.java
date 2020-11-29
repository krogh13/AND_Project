package com.example.todoprojectv2.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoprojectv2.model.ProjectRepository;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ProjectRepository repository;
    private LiveData<List<ToDoModelEntity>> allTodos;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProjectRepository(application);
        allTodos = repository.getAllToDos();
    }

    public void insert(String title, int priority) {
        repository.insert(new ToDoModelEntity(title, priority));
    }

    public void update(ToDoModelEntity toDoModelEntity) {
        repository.update(toDoModelEntity);
    }

    public void delete(ToDoModelEntity toDoModelEntity) {
        repository.delete(toDoModelEntity);
    }

    public void deleteAllToDos() {
        repository.deleteAllToDos();
    }

    public LiveData<List<ToDoModelEntity>> getAllToDos() {
        return allTodos;
    }

}
