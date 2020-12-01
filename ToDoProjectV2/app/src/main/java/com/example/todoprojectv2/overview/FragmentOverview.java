package com.example.todoprojectv2.overview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;
import com.example.todoprojectv2.viewmodel.ToDoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentOverview extends Fragment {

    private ToDoViewModel toDoViewModel;
    private OverviewAdapter adapter;
    private List<ToDoModelEntity> lists = new ArrayList<>();

   public FragmentOverview() {
        // Required empty public constructor
    }

    public static FragmentOverview newInstance() {
        FragmentOverview fragment = new FragmentOverview();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_oveview, container, false);

        // Recyclerview
        RecyclerView recyclerView = view.findViewById(R.id.overviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.hasFixedSize();

        adapter = new OverviewAdapter(lists);
        recyclerView.setAdapter(adapter);

        // Initialize ViewModel
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        toDoViewModel.getAllToDos().observe(getViewLifecycleOwner(), new Observer<List<ToDoModelEntity>>() {
            @Override
            public void onChanged(List<ToDoModelEntity> toDoModelEntities) {
                adapter.setToDos(toDoModelEntities);
            }
        });

        // FAB
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add_todo);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragmentTodo_to_addToDo);
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
                toDoViewModel.delete(adapter.getToDoAtPosition(viewHolder.getAdapterPosition()));

                // Create a toast to show it was deleted
                Toast.makeText(getContext(), "To Do deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        return view;
    }
}