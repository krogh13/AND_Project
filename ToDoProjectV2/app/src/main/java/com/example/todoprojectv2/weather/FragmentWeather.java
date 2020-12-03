package com.example.todoprojectv2.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.todoprojectv2.R;
import com.example.todoprojectv2.model.retrofit.WeatherResponse;
import com.example.todoprojectv2.viewmodel.api.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentWeather extends Fragment {

    private WeatherViewModel weatherViewModel;

    public FragmentWeather() {
        // Required empty public constructor
    }

    public static FragmentWeather newInstance() {
        FragmentWeather fragment = new FragmentWeather();
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
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // EditText city name
        final EditText editTextCityName = view.findViewById(R.id.editText_cityname);

        // Button search
        Button buttonSearch = view.findViewById(R.id.button_search);

        // TextView Temperature
        final TextView textViewTemperature = view.findViewById(R.id.textView_temperature);

        // TextView Humidity
        final TextView textViewHumidity = view.findViewById(R.id.textView_humidity);

        // TextView Feels like
        final TextView textViewFeelsLike = view.findViewById(R.id.textView_feelslike);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        weatherViewModel.getElements().observe(getViewLifecycleOwner(), new Observer<WeatherResponse.Main>() {
            @Override
            public void onChanged(WeatherResponse.Main mains) {
                textViewTemperature.setText(mains.temp);
                textViewHumidity.setText(mains.humidity);
                textViewFeelsLike.setText(mains.feels_like);
                            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherViewModel.getElements(editTextCityName.getText().toString());
            }
        });



        return view;
    }
}