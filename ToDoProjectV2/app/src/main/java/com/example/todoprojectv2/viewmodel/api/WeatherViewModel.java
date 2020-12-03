package com.example.todoprojectv2.viewmodel.api;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todoprojectv2.model.ProjectRepository;
import com.example.todoprojectv2.model.retrofit.WeatherResponse;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {

    private final ProjectRepository repository;

    public WeatherViewModel(Application application) {
        super(application);
        repository = ProjectRepository.getInstance(application);
    }

    public MutableLiveData<WeatherResponse.Main> getElements(String city) {
        return repository.getAllElements(city);
    }

    public MutableLiveData<WeatherResponse.Main> getElements() {
        return repository.getAllElements("");
    }
}
