package com.example.todoprojectv2.stickynotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoprojectv2.R;


public class FragmentStickyNotes extends Fragment {

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
        return inflater.inflate(R.layout.fragment_sticky_notes, container, false);
    }
}