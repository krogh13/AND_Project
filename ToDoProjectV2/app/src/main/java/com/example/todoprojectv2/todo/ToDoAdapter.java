package com.example.todoprojectv2.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {

    List<ToDoModelEntity> todoLists;

    public ToDoAdapter(List<ToDoModelEntity> lists) {
        this.todoLists = lists;
    }

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, int position) {
        ToDoModelEntity currentToDo = todoLists.get(position);

        holder.title.setText(currentToDo.getTitle());
        // Cast to string since the priority is a int
        holder.priority.setText(String.valueOf(currentToDo.getPriority()));
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }

    public void setToDos(List<ToDoModelEntity> todoLists) {
        this.todoLists = todoLists;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.ToDoTitle);
            priority = itemView.findViewById(R.id.ToDoPriority);
        }
    }

    // Used to get a certain position to make the swipe to delete work
    public ToDoModelEntity getToDoAtPosition(int position) {
        return todoLists.get(position);
    }
}
