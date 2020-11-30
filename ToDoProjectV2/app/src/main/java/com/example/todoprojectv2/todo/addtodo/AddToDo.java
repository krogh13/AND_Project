package com.example.todoprojectv2.todo.addtodo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.stickynotes.NoteViewModel;
import com.example.todoprojectv2.todo.ToDoViewModel;

import java.util.Date;

public class AddToDo extends Fragment {

    private ToDoViewModel toDoViewModel;
    private NoteViewModel noteViewModel;
    private long calenderDate;

        public AddToDo() {
        // Required empty public constructor
    }

    public static AddToDo newInstance() {
        AddToDo fragment = new AddToDo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_todo, container, false);

        // Initialize ViewModel
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        //EditText fields
        final EditText editTextTitle = view.findViewById(R.id.editText_title);
        final EditText getEditTextDescription = view.findViewById(R.id.editText_description);

        // NumberPicker
        final NumberPicker numberPickerPriority = view.findViewById(R.id.numberPicker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(5);

        // CalendarView
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calenderDate = calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date temp = new Date(year - 1900, month, dayOfMonth);
                calenderDate = temp.getTime();
            }
        });

        // Add button
        Button buttonAdd = view.findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextTitle.getText().toString())
                        && !TextUtils.isEmpty(getEditTextDescription.getText().toString())) {

                    // NoteFragment
                    noteViewModel.insert(editTextTitle.getText().toString()
                            , numberPickerPriority.getValue()
                            , new Date(calenderDate)
                            , getEditTextDescription.getText().toString());

                    Navigation.findNavController(view).navigate(R.id.fragmentTodo);
                    Toast.makeText(getContext(), "To Do is registered", Toast.LENGTH_SHORT).show();

                    //If the title is empty a toast will be shown
                } else if (TextUtils.isEmpty(editTextTitle.getText().toString())) {
                    Toast.makeText(getContext(), "A title must be entered!", Toast.LENGTH_LONG).show();

                    //If the description is empty a toast will be shown
                } else if (TextUtils.isEmpty(getEditTextDescription.getText().toString())) {
                    Toast.makeText(getContext(), "A description must be entered!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Cancel button
        Button buttonCancel = view.findViewById(R.id.button_cancel);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.fragmentTodo);
            }
        });

        return view;

    }
}