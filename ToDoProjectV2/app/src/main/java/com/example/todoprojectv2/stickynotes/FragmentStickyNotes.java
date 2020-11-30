package com.example.todoprojectv2.stickynotes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;
import com.example.todoprojectv2.todo.ToDoAdapter;
import com.example.todoprojectv2.todo.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentStickyNotes extends Fragment {

    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;
    private List<ToDoModelEntity> lists = new ArrayList<>();

    public FragmentStickyNotes() {
        // Required empty public constructor
    }

    public static FragmentStickyNotes newInstance() {
        FragmentStickyNotes fragment = new FragmentStickyNotes();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sticky_notes, container, false);

        // Recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.notesRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new NoteAdapter(lists);
        recyclerView.setAdapter(adapter);

        // Initialize ViewModel
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllToDos().observe(getViewLifecycleOwner(), new Observer<List<ToDoModelEntity>>() {
            @Override
            public void onChanged(List<ToDoModelEntity> toDoModelEntities) {
                adapter.setToDos(toDoModelEntities);
            }
        });

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getToDoAtPosition(viewHolder.getAdapterPosition()));

                // Create a toast to show it was deleted
                Toast.makeText(getContext(), "To Do deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        return view;

    }
}