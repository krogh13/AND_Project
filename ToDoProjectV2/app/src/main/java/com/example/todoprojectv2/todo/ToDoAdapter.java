package com.example.todoprojectv2.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.shared.ToDoModelEntity;

import java.text.DateFormat;
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
                .inflate(R.layout.recyclerview_item_todo, parent, false);
        return new ToDoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, int position) {
        //ToDoModelEntity currentToDo = todoLists.get(position);

        holder.title.setText(todoLists.get(position).getTitle());
        // Cast to string since the priority is a int
        holder.priority.setText(String.valueOf(todoLists.get(position).getPriority()));
        DateFormat dateFormat = DateFormat.getDateInstance();
        holder.date.setText(dateFormat.format(todoLists.get(position).getDate()));
        holder.description.setText(todoLists.get(position).getDescription());
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
        TextView date;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.NotesTitle);
            priority = itemView.findViewById(R.id.NotesPriority);
            date = itemView.findViewById(R.id.NotesDate);
            description = itemView.findViewById(R.id.NotesDescription);
        }
    }

    // Used to get a certain position to make the swipe to delete work
    public ToDoModelEntity getToDoAtPosition(int position) {
        return todoLists.get(position);
    }
}
