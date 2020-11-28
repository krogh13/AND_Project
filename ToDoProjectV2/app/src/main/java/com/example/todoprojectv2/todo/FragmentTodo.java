package com.example.todoprojectv2.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.ArrayList;
import java.util.List;

public class FragmentTodo extends Fragment {

    private ToDoViewModel toDoViewModel;
    private ToDoAdapter adapter;
    private List<ToDoModelEntity> lists = new ArrayList<>();

   public FragmentTodo() {
        // Required empty public constructor
    }

    public static FragmentTodo newInstance() {
        FragmentTodo fragment = new FragmentTodo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        // Recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.todoRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new ToDoAdapter(lists);
        recyclerView.setAdapter(adapter);

        // Initialize ViewModel
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        toDoViewModel.getAllToDos().observe(getViewLifecycleOwner(), new Observer<List<ToDoModelEntity>>() {
            @Override
            public void onChanged(List<ToDoModelEntity> toDoModelEntities) {
                adapter.setToDos(toDoModelEntities);
            }
        });


        return view;
    }
}